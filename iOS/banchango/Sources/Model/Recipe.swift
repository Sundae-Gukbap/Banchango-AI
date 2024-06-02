//
//  File.swift
//  banchango
//
//  Created by KimMinSeok on 6/3/24.
//

import Foundation

struct Recipe: Hashable{
    let id: Int
    let name: String
    let introduction: String
    let image: String
    let link: String
    let cookingTime: Int
    let servings: Int
    let difficulty: String
    let have: [Int]
    let need: [Int]
}

extension Recipe {
    static let list: [Recipe] = [
        Recipe(id: 1, name: "김치찌개", introduction: "김치찌개임", image: "dd", link: "dd", cookingTime: 30, servings: 30, difficulty: "5", have: [1,2,3,4], need: [1,2,3,4]),
        Recipe(id: 2, name: "순대국밥", introduction: "순대국밥임", image: "dd", link: "dd", cookingTime: 30, servings: 30, difficulty: "5", have: [1,2,3,4], need: [1,2,3,4]),
        Recipe(id: 3, name: "제육볶음", introduction: "제육볶음임", image: "dd", link: "dd", cookingTime: 30, servings: 30, difficulty: "5", have: [1,2,3,4], need: [1,2,3,4]),
        Recipe(id: 4, name: "계란말이", introduction: "계란말이임", image: "dd", link: "dd", cookingTime: 30, servings: 30, difficulty: "5", have: [1,2,3,4], need: [1,2,3,4])
    ]
}
