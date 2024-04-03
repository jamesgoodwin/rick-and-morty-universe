package com.jgoodwin.myapplication

import com.jgoodwin.burraco.domain.GameState
import org.junit.Assert.*
import org.junit.Test

class GameStateTest {

    @Test
    fun `newGame generates a GameState with correct initial conditions`() {
        val gameState = GameState.newGame()

        // Test the size of hands, pots, and deck
        assertEquals(11, gameState.playerHand.size)
        assertEquals(11, gameState.opponentHand.size)
        assertEquals(11, gameState.pots.first.size)
        assertEquals(11, gameState.pots.second.size)
        assertEquals("Deck should initially contain 64 cards", 64, gameState.deck.size)

        // Test that melds and discard pile are initialized correctly
        assertTrue("Player melds should be empty", gameState.playerMelds.isEmpty())
        assertTrue("Opponent melds should be empty", gameState.opponentMelds.isEmpty())
        assertTrue("Discard pile should be empty", gameState.discard.isEmpty())

        // Test uniqueness and completeness of the deck distribution
        val allCards = gameState.playerHand + gameState.opponentHand + gameState.pots.first + gameState.pots.second + gameState.deck
        assertEquals("All cards must be unique and account for the entire deck", 108, allCards.toSet().size)
    }

    @Test
    fun `newGame ensures no overlap between player hands, pots, and deck`() {
        val gameState = GameState.newGame()

        // Collect all the individual groups of cards
        val playerHandSet = gameState.playerHand.toSet()
        val opponentHandSet = gameState.opponentHand.toSet()
        val pot1Set = gameState.pots.first.toSet()
        val pot2Set = gameState.pots.second.toSet()
        val deckSet = gameState.deck.toSet()

        // Ensure there's no overlap between the different sets
        assertTrue("Player and opponent hands should not overlap", playerHandSet.intersect(opponentHandSet).isEmpty())
        assertTrue("Player hand and pot 1 should not overlap", playerHandSet.intersect(pot1Set).isEmpty())
        assertTrue("Player hand and pot 2 should not overlap", playerHandSet.intersect(pot2Set).isEmpty())
        assertTrue("Player hand and deck should not overlap", playerHandSet.intersect(deckSet).isEmpty())
        assertTrue("Opponent hand and pot 1 should not overlap", opponentHandSet.intersect(pot1Set).isEmpty())
        assertTrue("Opponent hand and pot 2 should not overlap", opponentHandSet.intersect(pot2Set).isEmpty())
        assertTrue("Opponent hand and deck should not overlap", opponentHandSet.intersect(deckSet).isEmpty())
        assertTrue("Pot 1 and pot 2 should not overlap", pot1Set.intersect(pot2Set).isEmpty())
        assertTrue("Pot 1 and deck should not overlap", pot1Set.intersect(deckSet).isEmpty())
        assertTrue("Pot 2 and deck should not overlap", pot2Set.intersect(deckSet).isEmpty())
    }
}
