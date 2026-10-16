# Livestream App

跨平臺直播應用程式，基於 **Kotlin Multiplatform (KMP)** 與 **Jetpack Compose Multiplatform** 開發，支援 **Android、iOS、Desktop 與 Web** 多平臺。

## 畫面預覽

| 直播主頁 (Live Main) | 直播間 (Living Room) |
|---|---|
| ![Livestream Preview 1](docs/images/6188297723598017386.jpg) | ![Livestream Preview 2](docs/images/6188297723598017387.jpg) |

---

## 專案架構與技術特點

- **Kotlin Multiplatform (KMP)**: 核心商業邏輯與 UI 邏輯共用，實現真正的跨平臺程式碼複用。
- **Compose Multiplatform**: 宣告式 UI 介面，提供流暢的跨平臺一致性體驗。
- **依賴注入 (Koin)**: 使用 Koin 進行模組化依賴注入（支援 `viewModel`、`single`、`factory` 等）。
- **導航 (Navigation)**: 採用型別安全路由（Type-safe Navigation）進行畫面切換（如主頁分頁切換、直播間導航）。
- **狀態管理 (ViewModel)**: 透過 `LiveStreamFeedViewModel` 與 `LiveStreamViewModel` 管理串流、動態推薦、聊天室及互動狀態。
- **多平臺支援**:
  - **Android**: 原生 App 執行環境。
  - **iOS**: 透過 Compose Multiplatform 產出 Shared Framework 並整合至 Xcode (`iosApp`)。
  - **Desktop**: 支援 Compose Desktop 執行。
  - **Web**: 支援 Web 執行環境。

## 功能模組

1. **直播首頁 (LiveMainScreen)**:
   - 橫幅專區 (BannerSection)
   - 每日推薦卡片 (DailyRecommendCard)
   - 主播動態列表 (AnchorCard / AnchorHeader)
   - 支援捲動時自動隱藏/顯示底部導覽列 (BottomNavigation)
2. **直播間 (LivingRoomScreen)**:
   - 聊天室列表 (ChatListView)
   - 底部互動操作欄 (BottomActionBar)
   - 熱度值徽章 (HeatValueBadge)、VIP 標誌等互動元素
3. **底部導覽列 (BottomNavigation)**:
   - 直播、聲播、廣場、消息、設定等多元分頁。
