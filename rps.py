import random

rock = """
   Rock
    _______
---'   ____)
      (_____)
      (_____)
      (____)
---.__(___)
"""

paper = """
   Paper
    _______
---'   ____)____
          ______)
          _______)
         _______)
---.__________)
"""

scissors = """
   Scissors
    _______
---'   ____)____
          ______)
       __________)
      (____)
---.__(___)
"""

def rps():
    while i == 1:
        user1 = (int)(input("Enter 1 for Rock, 2 for Scissors, 3 for Paper:\n"))
        print("You picked")
        if user1 == 1:
            print(rock)
        elif user1 == 2:
            print(scissors)
        elif user1 == 3:
            print(paper)

        comp = random.randint(1, 3)
        print("Computer picked")
        if comp == 1:
            print(rock)
        elif comp == 2:
            print(scissors)
        elif comp == 3:
            print(paper)

        if comp == user1:
            print("It is a tie;")
        elif (
            (comp == 1 and user1 == 2)
            or (comp == 2 and user1 == 3)
            or (comp == 3 and user1 == 1)
        ):
            print("You lose")
        elif (
            (comp == 1 and user1 == 3)
            or (comp == 3 and user1 == 2)
            or (comp == 2 and user1 == 1)
        ):
            print("You win")
        replay = int(input("Do you want to play again(write 1 for yes or 2 for no):"))
        if replay == 2:
            print("Thank you for playing")
            break


print("Welcome to Rock - Paper - Scissors!")
print(
    "The rules are simple: Rock beats scissors, Scissors beats paper, Paper beats rock."
)
i = 1
rps()


# print the choice the computer made

# determine who won the game (think about how you can determine who won in a real life game of rock paper scissors)

# BONUS: Make it so the game keeps going unless the user presses a specific button to make it stop
