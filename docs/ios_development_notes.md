# iOS 開發與 KMP 遷移問題修復筆記

本文件紀錄了將 LANG 專案（Compose Multiplatform）從 Android/Desktop 延伸至 iOS 平台時遇到的主要問題與解決方案。

## 1. 建置與基礎設施 (Build & Infrastructure)

### 1.1 Framework 命名與引用
*   **問題**：Gradle 預設輸出的 framework 名稱為小寫 `shared.framework`，但 Xcode 專案中常引用為大寫 `Shared`。
*   **修復**：在 `shared/build.gradle.kts` 中明確定義 `baseName = "Shared"`。
*   **路徑配置**：在 Xcode 專案配置中加入 `FRAMEWORK_SEARCH_PATHS`，指向 `$(SRCROOT)/../../shared/build/xcode-frameworks/$(CONFIGURATION)/$(SDK_NAME)`。

### 1.2 Xcode Build Phase 腳本修復
*   **路徑偏移**：由於 `iosApp` 目錄層級與 Root 不同，腳本中的 `cd` 路徑必須修正為 `cd "$SRCROOT/../.."` 才能找到 `gradlew`。
*   **環境變數**：在 macOS 上執行 `gradlew` 建立服務時，必須 export `ANDROID_PREFS_ROOT=$HOME` 避免權限與目錄建立失敗。

---

## 2. Jetpack Room (iOS 平台適配)

### 2.1 強制構造器註解
*   **問題**：Room 2.7.0+ 在非 Android 平台要求必須使用 `@ConstructedBy`。
*   **修復**：
    ```kotlin
    @Database(...)
    @ConstructedBy(LangDatabaseConstructor::class)
    abstract class LangDatabase : RoomDatabase()

    expect object LangDatabaseConstructor : RoomDatabaseConstructor<LangDatabase>
    ```
*   **實作**：iOS 端使用 `factory = { LangDatabaseConstructor.initialize() }` 進行初始化。

### 2.2 資料庫儲存路徑
*   **修復**：iOS 端必須將資料庫放在 `NSDocumentDirectory` 下，否則會因沙盒權限導致無法寫入。

---

## 3. 網路與 WebSocket (Ktor Darwin)

### 3.1 Flow Invariant Violation
*   **問題**：Ktor 的 `Darwin` 引擎在連線回調時會切換協程上下文，導致在普通的 `flow { ... }` 中呼叫 `emit()` 報錯。
*   **修復**：改用 **`channelFlow { ... }`**，利用線程安全的 `send()` 替代 `emit()`。

### 3.2 Socket.io 4 握手時序
*   **問題**：iOS 上連線較為嚴格，若未收到 Server 的 Open 包 (0) 就發送空間包 (40)，會被 Server 拒絕。
*   **修復**：改為響應式握手：
    1. 接收 `0{...}` (Engine.io Open)
    2. 發送 `40/chat_nsp,` (Socket.io Connect)
    3. 接收 `40/chat_nsp,`
    4. 發送 `42/chat_nsp,["authentication", ...]` (Auth)

### 3.3 Darwin 引擎 Header 敏感度
*   **Host Header**：移除手動設定的 `HttpHeaders.Host`，交由系統自動管理，避免 403。
*   **ContentType**：發起 WS 握手時必須強制將 `Accept` 與 `Content-Type` 設為 `null`，避免 Ktor 嘗試對 Session 進行序列化。

### 3.4 認證封包細節
*   **修復**：認證 Data Class 必須配合 `Json { encodeDefaults = true }`，確保 `client_type` 等帶有預設值的欄位會被包含在字串中發送給 Server。

---

## 4. 依賴注入 (Koin)

### 4.1 平台模組補全
*   **修復**：在 `MainViewController` 啟動時，必須顯式注入 `iosPlatformModule`，提供 `Darwin` 引擎實作與 `BrowserIdProvider`。

### 4.2 Qualifier 覆蓋問題
*   **問題**：在 Koin 模組中定義多個同類型的 `single` 若無名稱會互相覆蓋。
*   **修復**：為 `ServicePatternContribution` 加入 `named("LANG_CHAT_PATTERN")` 等標識，確保 `getAll()` 能取回完整的服務映射表。
