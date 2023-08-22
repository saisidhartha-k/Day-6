package Controller;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Random;

public class CardContoller {
	static String[] cardNames = {
		"2H", "3H", "4H", "5H", "6H", "7H", "8H", "9H", "10H", "JH", "QH", "KH", "AH",
		"2D", "3D", "4D", "5D", "6D", "7D", "8D", "9D", "10D", "JD", "QD", "KD", "AD",
		"2C", "3C", "4C", "5C", "6C", "7C", "8C", "9C", "10C", "JC", "QC", "KC", "AC",
		"2S", "3S", "4S", "5S", "6S", "7S", "8S", "9S", "10S", "JS", "QS", "KS", "AS"
	};

	ArrayList<String> cardSet ;
	static ArrayList<String> inList ;
	static ArrayList<String> outList ;

	public CardContoller()
	{
		cardSet = new ArrayList<>();
		inList = new ArrayList<>();
		outList = new ArrayList<>();

		shuffle(cardNames);
		processCard();
	}

	public void processCard()
	{
		int totalCards = cardNames.length;

        for (int i = 0; i < totalCards; i++) {
            if (i < totalCards / 2) {
                inList.add(cardNames[i]);
				cardSet.add(cardNames[i]);
            } else {
                outList.add(cardNames[i]);
				cardSet.add(cardNames[i]);

            }
        }
	}
	
	public static void shuffle(String[] array) {
System.out.println("shuffle");
			Random random = new Random();
	
			for (int i = array.length - 1; i > 0; i--) {
				int j = random.nextInt(i + 1);
	
				String temp = array[i];
				array[i] = array[j];
				array[j] = temp;
			}
		}
		
//    public static void playGame() {
//       
//    	CardContoller t = new CardContoller();
//    	Player p1 = new Player("p1", 10, "7C", false);
//    	Player p2 = new Player("p2", 20, "8C", true);
//
//		play(t,p1,p2);
//        // for (int i=0 ; i<cardNames.length;i++) {
//        //    System.out.println(cardNames[i]);
//        //     }
//
//		System.out.println(p1.win);
//		System.out.println(p2.win);
//		System.out.println(p1.winMoney);
//		System.out.println(p2.winMoney);
//        }

	public static void play(CardContoller t, Player p1, Player p2) {
		
		System.out.println(p1.name);
		System.out.println(p1.card);
		System.out.println(p2.name);
		System.out.println(p2.card);
		
		System.out.println(t.cardSet.isEmpty());
		int i = 0;
		while (!t.cardSet.isEmpty())
		{
			String randomCard =  getRandomCard(t.cardSet);
//			System.out.println(randomCard);
			if(t.inList.contains(randomCard))
			{
				System.out.println("iam inside p1 inslist");
				System.out.println(randomCard);

				if(p1.getCard() == randomCard)
				{
					p1.win=true;
					p1.winner(p2);
					System.out.println(p1.winMoney);
					System.out.println(p1.win);
					System.out.println("found p1");

					return;
				}
			}
			t.cardSet.remove(randomCard);
			 randomCard =  getRandomCard(t.cardSet);

//			System.out.println(randomCard);

			if (t.outList.contains(randomCard))
			{
				System.out.println("iam inside p2 outslist");
				System.out.println(randomCard);
				System.out.println(p2.card);
				if(p2.getCard() == randomCard)
				{
					p2.win=true;
					p2.winner(p1);
					System.out.println(p2.win);

					System.out.println(p2.winMoney);
					System.out.println("found p2");
					return;
				}
			}
			t.cardSet.remove(randomCard);

		}
	}

	public static String getRandomCard(ArrayList<String> cardSet2) {
        int randomIndex = new Random().nextInt(cardSet2.size());
        int currentIndex = 0;
        for (String card : cardSet2) {
            if (currentIndex == randomIndex) {
                return card;
            }
            currentIndex++;
        }
        return null; 
    }
    }
