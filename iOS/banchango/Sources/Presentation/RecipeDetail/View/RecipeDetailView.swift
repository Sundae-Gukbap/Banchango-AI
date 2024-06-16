//
//  RecipeDetailView.swift
//  banchango
//
//  Created by KimMinSeok on 6/16/24.
//

import SwiftUI

struct RecipeDetailView: View {
    
    let recipeid:Int
    
    var body: some View {
        Text(String(recipeid))
    }
}

#Preview {
    RecipeDetailView(recipeid:6952728)
}
