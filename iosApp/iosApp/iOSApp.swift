import SwiftUI
import Shared

@main
struct iOSApp: App {
    init() {
        LiveViewControllerKoinHelperKt.doInitKoin()
    }

    var body: some Scene {
        WindowGroup {
            ContentView()
        }
    }
}
