//
//  DetailRecipe.swift
//  banchango
//
//  Created by KimMinSeok on 6/16/24.
//

import Foundation

struct DetailRecipe: Hashable, Codable{
    let id: Int
    let name: String
    let introduction: String
    let image: String
    let link: String
    let have: [String]
    let need: [String]
    let servings: Int
    let cookingTime: Int
    let difficulty: String
    
    private enum CodingKeys: String, CodingKey {
        case id = "id"
        case name = "name"
        case introduction = "introduction"
        case image = "image"
        case link = "link"
        case have = "have"
        case need = "need"
        case servings = "servings"
        case cookingTime = "cookingTime"
        case difficulty = "difficulty"
    }
}
