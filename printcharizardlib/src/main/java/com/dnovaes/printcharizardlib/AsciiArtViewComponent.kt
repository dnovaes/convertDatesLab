package com.dnovaes.printcharizardlib

import android.content.Context
import android.graphics.Color
import com.dnovaes.commons.components.FlexboxLayoutComponent
import com.dnovaes.commons.components.highOrderComponent
import com.google.android.flexbox.FlexDirection
import com.google.android.flexbox.FlexWrap
import trikita.anvil.BaseDSL
import trikita.anvil.BaseDSL.MATCH
import trikita.anvil.BaseDSL.WRAP
import trikita.anvil.BaseDSL.dip
import trikita.anvil.BaseDSL.size
import trikita.anvil.BaseDSL.text
import trikita.anvil.DSL.textColor
import trikita.anvil.DSL.textView

inline fun asciiArtViewComponent(crossinline func: AsciiArtViewComponent.() -> Unit) {
    highOrderComponent(func)
}

class AsciiArtViewComponent(context: Context) : FlexboxLayoutComponent(context) {

    companion object {
        const val ASCII_DEFAULT_DIP_TABLET = 8
        val TEXT_DEFAULT_BLACK = Color.parseColor("#37383D")
        val charizardContent = "                 .-,.__\n" +
                "                 `.     `.  ,\n" +
                "              .--'  .._,'\"-' `.\n" +
                "             .    .'         `'\n" +
                "             `.   /          ,'\n" +
                "               `  '--.   ,-\"'\n" +
                "                `\"`   |  \\\n" +
                "                   -. \\, |\n" +
                "                    `--Y.'      ___.\n" +
                "                         \\     L._, \\\n" +
                "               _.,        `.   <  <\\                _\n" +
                "             ,' '           `, `.   | \\            ( `\n" +
                "          ../, `.            `  |    .\\`.           \\ \\_\n" +
                "         ,' ,..  .           _.,'    ||\\l            )  '\".\n" +
                "        , ,'   \\           ,'.-.`-._,'  |           .  _._`.\n" +
                "      ,' /      \\ \\        `' ' `--/   | \\          / /   ..\\\n" +
                "    .'  /        \\ .         |\\__ - _ ,'` `        / /     `.`.\n" +
                "    |  '          ..         `-...-\"  |  `-'      / /        . `.\n" +
                "    | /           |L__           |    |          / /          `. `.\n" +
                "   , /            .   .          |    |         / /             ` `\n" +
                "  / /          ,. ,`._ `-_       |    |  _   ,-' /               ` \\\n" +
                " / .           \\\"`_/. `-_ \\_,.  ,'    +-' `-'  _,        ..,-.    \\`.\n" +
                ".  '         .-f    ,'   `    '.       \\__.---'     _   .'   '     \\ \\\n" +
                "' /          `.'    l     .' /          \\..      ,_|/   `.  ,'`     L`\n" +
                "|'      _.-\"\"` `.    \\ _,'  `            \\ `.___`.'\"`-.  , |   |    | \\\n" +
                "||    ,'      `. `.   '       _,...._        `  |    `/ '  |   '     .|\n" +
                "||  ,'          `. ;.,.---' ,'       `.   `.. `-'  .-' /_ .'    ;_   ||\n" +
                "|| '              V      / /           `   | `   ,'   ,' '.    !  `. ||\n" +
                "||/            _,-------7 '              . |  `-'    l         /    `||\n" +
                ". |          ,' .-   ,' ||               | .-.        `.      .'     ||\n" +
                " `'        ,'    `\".'    |               |    `.        '. -.'       `'\n" +
                "          /      ,'      |               |,'    \\-.._,.'/'\n" +
                "          .     /        .               .       \\    .''\n" +
                "        .`.    |         `.             /         :_,'.'\n" +
                "          \\ `...\\   _     ,'-.        .'         /_.-'\n" +
                "           `-.__ `,  `'   .  _.>----''.  _  __  /\n" +
                "                .'        /\"'          |  \"'   '_\n" +
                "               /_|.-'\\ ,\".             '.'`__'-( \\\n" +
                "                 / ,\"'\"\\,'               `/  `-.| mh"
    }

    override fun view() {
        size(MATCH, WRAP)
        BaseDSL.attr("flexDirection", FlexDirection.ROW)
        BaseDSL.attr("flexWrap", FlexWrap.WRAP)

        charizardContent.forEachIndexed { _, c ->
            when(c) {
                ' ' -> {
                    textView {
                        size(dip(ASCII_DEFAULT_DIP_TABLET), WRAP)
                        text("")
                        textColor(TEXT_DEFAULT_BLACK)
                    }
                }
                '\n' -> {
                    textView {
                        size(MATCH, dip(1))
                        text("")
                        textColor(TEXT_DEFAULT_BLACK)
                    }
                }
                else -> {
                    textView {
                        size(dip(ASCII_DEFAULT_DIP_TABLET), WRAP)
                        text(c.toString())
                        textColor(TEXT_DEFAULT_BLACK)
                    }
                }
            }
        }
    }
}
