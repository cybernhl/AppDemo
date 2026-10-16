# IM App

跨平臺即時通訊 (Instant Messaging) 應用程式，基於 **Kotlin Multiplatform (KMP)** 與 **Jetpack Compose Multiplatform** 開發，支援 **Android、iOS、Desktop 與 Web** 多平臺。

## 畫面預覽

| 好友列表 / 主頁 (Friend List) | 聊天詳情 (Chat Detail) |
|---|---|
| ![IM Preview 1](docs/images/im1.png) | ![IM Preview 2](docs/images/im2.png) |

---

## 專案架構與技術特點

- **Kotlin Multiplatform (KMP)**: 核心商業邏輯、資料層與 UI 共用，實現跨平臺高程式碼複用率。
- **Compose Multiplatform**: 宣告式 UI 介面，提供流暢且一致的跨平臺使用者體驗。
- **本地資料庫 (Room Database)**: 整合 Room 進行資料持久化（包含好友資料 `FriendDao`、聊天訊息 `ChatMessageDao` 與訊息持久化服務 `MessagePersister`）。
- **依賴注入 (Koin)**: 模組化依賴注入架構 (`sharedModule`、`appModule` 等)。
- **狀態管理 (ViewModel)**:
  - `ChatViewModel`: 處理聊天訊息收發與對話紀錄管理。
  - `FriendListViewModel`: 管理好友列表與聯絡人狀態。
- **多平臺支援**:
  - **Android**: 原生 Android 應用程式執行。
  - **iOS**: 透過 Compose Multiplatform 產出 Shared Framework 並與 Xcode 專案 (`iosApp`) 深度整合。
  - **Desktop**: 支援跨平臺案頭端 (Desktop) 執行。
  - **Web**: 支援 Web 端運行。

## 功能模組

1. **好友與聯絡人列表 (FriendListScreen)**:
   - 好友頭像與個人檔案檢視 (`FriendProfileScreen`)
   - 聯絡人狀態管理
2. **即時通訊聊天室 (ChatDetailScreen / ChatListScreen)**:
   - 聊天訊息收發與歷史紀錄
   - 訊息自動持久化 (`MessagePersister`)
3. **底部導覽與路由 (MainScreen)**:
   - 多分頁切換與導覽系統。
