import SwiftUI
import Shared

@main
struct iOSApp: App {
    init() {
        IMMainViewControllerKoinHelperKt.doInitIMKoin()
    }
    var body: some Scene {
        WindowGroup {
            ContentView()
        }
    }
}