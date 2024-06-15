//
//  SwiftUIView.swift
//  banchango
//
//  Created by KimMinSeok on 6/3/24.
//

import SwiftUI

struct RecipeRecommendView: View {
    @ObservedObject private var recipeRecommendVM: RecipeRecommendViewModel
    
    init() {
        self.recipeRecommendVM = RecipeRecommendViewModel()
        recipeRecommendVM.getRecipeRecommend()
    }
    
    let colors:[Color] = [.purple, .pink, .orange]
    
    var body: some View {
        if recipeRecommendVM.recipes.isEmpty {
            Text("Loading...") // Placeholder text while loading
                .font(.largeTitle)
        } else {
            GeometryReader { proxy in
                ScrollView(.vertical) {
                    VStack(spacing: 0) {
                        ForEach(recipeRecommendVM.recipes.indices, id: \.self) { index in
                            ZStack {
                                colors[index % colors.count]
                                Circle()
                                    .fill(Color.white)
                                    .frame(width: 200, height: 200)
                                    .overlay(
                                        VStack {
                                            Text(recipeRecommendVM.recipes[index].name)
                                                .font(.largeTitle)
                                                .foregroundColor(.black)
                                            Text(recipeRecommendVM.recipes[index].introduction)
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
}

#Preview {
    RecipeRecommendView()
}
