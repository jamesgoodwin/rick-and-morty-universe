package com.jgoodwin.myapplication

import PlayingCard
import com.jgoodwin.burraco.domain.Suit
import com.jgoodwin.burraco.domain.Value
import convertToPlayingCard
import org.junit.jupiter.api.Assertions.*

import org.junit.Test
import org.junit.jupiter.api.assertThrows

class IntToPlayingCardTest {

    @Test
    fun `Ace of each suit is correctly mapped`() {
        assertEquals(PlayingCard(Value.Ace, Suit.Clubs), convertToPlayingCard(1))
        assertEquals(PlayingCard(Value.Ace, Suit.Diamonds), convertToPlayingCard(14))
        assertEquals(PlayingCard(Value.Ace, Suit.Hearts), convertToPlayingCard(27))
        assertEquals(PlayingCard(Value.Ace, Suit.Spades), convertToPlayingCard(40))
    }

    @Test
    fun `King of each suit is correctly mapped`() {
        assertEquals(PlayingCard(Value.King, Suit.Clubs), convertToPlayingCard(13))
        assertEquals(PlayingCard(Value.King, Suit.Diamonds), convertToPlayingCard(26))
        assertEquals(PlayingCard(Value.King, Suit.Hearts), convertToPlayingCard(39))
        assertEquals(PlayingCard(Value.King, Suit.Spades), convertToPlayingCard(52))
    }

    @Test
    fun `Jokers are correctly mapped`() {
        assertEquals(PlayingCard(Value.Joker, Suit.Spades), convertToPlayingCard(53))
        assertEquals(PlayingCard(Value.Joker, Suit.Spades), convertToPlayingCard(54))
        assertEquals(PlayingCard(Value.Joker, Suit.Spades), convertToPlayingCard(107))
        assertEquals(PlayingCard(Value.Joker, Suit.Spades), convertToPlayingCard(108))
    }

    @Test
    fun `Values are correctly mapped within a suit`() {
        assertEquals(PlayingCard(Value.Two, Suit.Clubs), convertToPlayingCard(2))
        assertEquals(PlayingCard(Value.Three, Suit.Clubs), convertToPlayingCard(3))
    }

    @Test
    fun `Suits are correctly mapped`() {
        // Assuming 1-13 are Clubs, 14-26 are Diamonds, etc.
        assertTrue(convertToPlayingCard(10).suit == Suit.Clubs)
        assertTrue(convertToPlayingCard(24).suit == Suit.Diamonds)
        assertTrue(convertToPlayingCard(38).suit == Suit.Hearts)
        assertTrue(convertToPlayingCard(52).suit == Suit.Spades)
    }

    @Test
    fun `Invalid inputs are handled appropriately`() {
        assertThrows<IllegalArgumentException> { convertToPlayingCard(0) }
        assertThrows<IllegalArgumentException> { convertToPlayingCard(109) }
    }
}
