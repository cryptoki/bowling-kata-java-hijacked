# How to
## start the game
```./gradlew run -q --console=plain```
or in the IDE
or use plain java cli

# Bowling Kata Java
## Domain
### Game Setup
* Game = 10 Frames
* Frame up to 2 Rolls
  * Exception: 10th frame can have up to 3 Rolls if Strike or Spare
* Roll up to 10 Pins

### Score
#### Frame
* Count Pins
* plus Bonix

#### Spare Boni
##### Precondition
* 10 Pins in one Frame (with the second roll - I missed the info in the exercise sheet)
##### Score calculation
* 10 Pins plus pins of next roll in the next frame

#### Strike Boni
##### Precondition
* 10 Pins in the first roll of the frame
##### Score calculation
* 10 Pins plus pins of the next TWO rolls in the next frame

# Useful Game Tests
## Gutter Game
* 20 times 0
* Score = 0

## Every Roll is 1
* 20 times 1
* Score = 20

## Spare followed by 5 (3 times 5)
* 3 times 5
* 17 times 0
* Score = 20

## Strike followed by 3 and 5
* 10, 3 and 5
* 16 times 0
* Score = 26

## Perfect Game
* 11 times 10
* Score = 300

## Game in the exercise sheet
* 1,4  4,5  6,4(/)  5,5(/)  10(X)  0,1  7,3(/)  6,4(/)  10(X)  2,8(/),6
* Score = 133


# Add on
* additional tasks are and input and output user interface
* it can be text based
the additional task will be done after the original kata


# Approach
1. TDD and normal kata setup except, I'm alone.
2. Take a clock with timer ;-)
3. I adjust the maximum time to 3, it's sunday :-)
4. commit after each cycle or revert if the 3 minutes are reached
5. the whole kata are only allowed to take up to 3 hours. Stop work if the time is reached