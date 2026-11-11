import UIKit
import SwiftUI
import Shared

struct ComposeView: UIViewControllerRepresentable {
    private var siteId: String {
        Bundle.main.object(forInfoDictionaryKey: "SiteId") as? String ?? ""
    }

    private var agentId: String {
        Bundle.main.object(forInfoDictionaryKey: "AgentId") as? String ?? ""
    }

    func makeUIViewController(context: Self.Context) -> UIViewController {
        MainViewControllerKt.MainViewController(
            siteId: siteId,
            agentId: agentId,
            initialToken: nil
        )
    }

    func updateUIViewController(_ uiViewController: UIViewController, context: Self.Context) {}
}

struct ContentView: View {
    var body: some View {
        ComposeView()
            .ignoresSafeArea()
    }
}