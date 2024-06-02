//
//  SwiftUIView.swift
//  banchango
//
//  Created by KimMinSeok on 6/3/24.
//

import SwiftUI

struct RecipeRecommendView: View {
    let colors:[Color] = [.purple, .pink, .orange]
    let texts: [String] = ["순대국밥", "김치찌개", "Recipe 3"]
    
    var body: some View {
            GeometryReader { proxy in
                ScrollView(.vertical) {
                    VStack(spacing: 0) {
                        ForEach(colors.indices, id: \.self) { index in
                            ZStack {
                                colors[index]
                                Circle()
                                    .fill(Color.white)
                                    .frame(width: 200, height: 200)
                                    .overlay(
                                        Text(texts[index])
                                            .font(.largeTitle)
                                            .foregroundColor(.black)
                                    )
                            }
                            .frame(width: proxy.size.width, height: proxy.size.height)
                        }
                    }
                }
            }.onAppear() {
                UIScrollView.appearance().isPagingEnabled = true
            }
        }
}

#Preview {
    RecipeRecommendView()
}
