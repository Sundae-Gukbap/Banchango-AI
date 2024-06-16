//
//  File.swift
//  banchango
//
//  Created by KimMinSeok on 6/3/24.
//

import Foundation

struct Recipe: Hashable, Codable{
    let id: Int
    let name: String
    let introduction: String
    let image: String
    let link: String
    let have: [String]
    let need: [String]
    let servings: Int
    let cookingTime: Int
    let isBookmarked: Bool?
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
        case isBookmarked = "isBookmarked"
        case difficulty = "difficulty"
    }
}
