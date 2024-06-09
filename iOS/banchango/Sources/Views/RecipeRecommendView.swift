//
//  SwiftUIView.swift
//  banchango
//
//  Created by KimMinSeok on 6/3/24.
//

import SwiftUI

struct RecipeRecommendView: View {
    let colors:[Color] = [.purple, .pink, .orange]
    let recipes: [Recipe]
    
    var body: some View {
            GeometryReader { proxy in
                ScrollView(.vertical) {
                    VStack(spacing: 0) {
                        ForEach(recipes.indices, id: \.self) { index in
                            ZStack {
                                colors[index % colors.count]
                                Circle()
                                    .fill(Color.white)
                                    .frame(width: 200, height: 200)
                                    .overlay(
                                        VStack {
                                            Text(recipes[index].name)
                                                .font(.largeTitle)
                                                .foregroundColor(.black)
                                            Text(recipes[index].introduction)
                                                .font(.caption)
                                                .foregroundColor(.gray)
                                                .multilineTextAlignment(.center)
                                        }
                                        .padding()
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
    RecipeRecommendView(recipes: Recipe.list)
}
