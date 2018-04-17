package com.conghuy.conggiaomanager.controller.leftMenu

class MenuDto {
    var title: String? = null
    var anEnum: MenuEnum? = null

    constructor(anEnum: MenuEnum?, title: String?) {
        this.anEnum = anEnum
        this.title = title
    }
}
