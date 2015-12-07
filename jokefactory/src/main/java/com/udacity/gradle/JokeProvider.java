package com.udacity.gradle;

public class JokeProvider {

    // Leave index 0 as the Test Joke
    private final static String [] jokeArray = {
            "What's brown and sticky?\n\nA stick!",
            "What happens to a frog's car when it breaks down?\n\nIt gets toad away!",
            "Why was six scared of seven?\n\nBecause seven 'ate' nine!",
            "How do you count cows?\n\nWith a cowculator!",
            "What do you call a bear with no teeth?\n\nA gummy bear!",
            "How do astronomers organize a party?\n\nThey planet!",
            "I was wondering why the ball kept getting bigger and bigger, and then it hit me.",
            "Did you hear about the guy whose whole left side was cut off?\n\nHe's all right now.",
            "A termite walks into the bar and asks, \"Is the bar tender here?\"",
            "What did the pirate say when he turned 80?\n\nAye Matey!",
            "A computer scientist's wife asks him, \"would you pick up a loaf of bread at the store ," +
                    "and if they have eggs get a dozen?\"\nHe gets home, throws 12 loaves of bread " +
                    "on the counter, and says \"they had eggs\"",
            "What did they give the guy who invented the doorknocker?\n\nA no-bell prize.",
            "What do you get when you cross a dyslexic, an insomniac, and an agnostic?\n\n" +
                    "Someone who lays awake at night wondering if there is a dog.",
            "How many tickles does it take to make an octopus laugh?\n\nTen tickles!",
            "What is red and smells like blue paint?\n\nRed paint.",
            "I never wanted to believe that my Dad was stealing from his job as a road worker.\n\n" +
                    "But when I got home, all the signs were there.",
            "I poured root beer into a square cup. Now I just have beer.",
            "Descartes walks into a bar. The bartender asks him if he wants a drink.\n\n" +
                    "\"I think not.\" he replies...and disappears.",
            "Why did the scarecrow win an award?\n\n He was outstanding in his field.",
            "Where does the General keep his armies?\n\nIn his sleevies...",
            "Why couldn't the bicycle stand up?\n\nBecause it was two tired",
            "Did you hear about the two guys that stole a calender?\n\nThey both got 6 months!",
            "A grasshopper walks into a bar. The bartender says to him, \"Hey, we have a drink named" +
                    " after you.\" The grasshopper replies, \"You have a drink named Murray?\"",
            "What's E.T. short for?\n\nBecause he has little legs.",
            "There are two types of people in the world. Those who can extrapolate from incomplete data...",
            "Whats orange and sounds like a parrot?\n\nA carrot",
            "Two cows are standing in a field and one cow says to the other: \"What do you think " +
                    "about that mad cow disease?\", the other cow responds: \"What do I care \"I'm a helicopter\"",
            "What type of pants does Mario wear?\n\ndenim denim denim",
            "How many eggs are there in a French omelet?\n\nJust one, because one egg is un oeuf.",
            "How much does a hipster weigh?\n\nAn Instagram!"
    };

    public String getJoke(int index) {
        if (index > -1 && index < jokeArray.length) {
            return jokeArray[index];
        } else {
            Integer randomIndex = Utility.getRandBetween(0, jokeArray.length);
            return jokeArray[randomIndex];
        }
    }
}
