# CS611_MonsterAndHero

## Project Overview

**Legends: Monsters and Heroes** is a Java-based text-based adventure RPG. Players can create a team of heroes, explore a magical world, battle various monsters, collect equipment, level up, and become legendary heroes!

### ✨ Key Features

- 🏰 **Diverse Hero Classes**: Warrior, Paladin, Sorcerer

- 👹 **Rich Monster System**: Dragons, Spirits, Exoskeletons

- ⚔️ **Equipment System**: Weapons, Armor, Potions, Spells

- 🌍 **World Exploration**: Walkable areas, obstacles, markets, combat zones

- 💰 **Trading System**: Buy and sell equipment, manage gold

- 🎯 **Strategic Combat**: Turn-based combat, skill casting, dodge mechanics
  

## 🚀 Quick Start

### System Requirements
- **Java SDK 8** or later

- **IntelliJ IDEA** or any Java IDE

- **Operating System**: Windows, macOS, Linux
  
### 🔧 Installation and Running

1. **Clone the project**
``` bash
git clone git@github.com:ShuxunZhou/CS611_MonsterAndHero.git
```
2. **Compile the project**
``` bash
javac -d out src/legends/game/*.java src/legends/**/*.java
```
3. **Run the game**
``` bash
java -cp out legends.game.Game
```

## 🏗️ Project Structure
``` 
MonsterAndHero/
├── src/
│   └── legends/
│       ├── battle/              # Battle System
│       │   └── Battle.java
│       ├── character/           # Character System
│       │   ├── LivingEntity.java        # Creature Base Class
│       │   ├── Hero.java                # Hero Base Class
│       │   ├── Monster.java             # Monster Base Class
│       │   ├── Warrior.java             # Warrior Class
│       │   ├── Paladin.java             # Paladin Class
│       │   ├── Sorcerer.java            # Sorcerer Class
│       │   ├── Dragon.java              # Dragon Monster
│       │   ├── Spirit.java              # Undead Monster
│       │   └── Exoskeleton.java         # Exoskeleton Monster
│       ├── data/                # Data Management
|       │   └── GameDataLoader.java  # Reference data
│       ├── game/                # Main game logic
│       │   └── Game.java                # Game entry point
│       ├── items/               # Item system
│       │   ├── Item.java                # Base item class
│       │   ├── Weapon.java              # Weapon
│       │   ├── Armor.java               # Armor
│       │   ├── Potion.java              # Potion
│       │   ├── Spell.java               # Spell
│       │   └── Inventory.java           # Inventory system
│       ├── market/              # Market system
│       └── world/               # World map
└── README.md
```

## 🎯 Gameplay

### 🦸‍♂️ Hero System

#### Hero Classes

1. **Warrior**

- 💪 High attack power and health

- 🛡️ Excels in melee combat

- ⚔️ Suitable for beginners

2. **Paladin**

- ⚖️ Balanced attack power and mana

- 🛡️ Good defensive capabilities

- ✨ Can use some spells

3. **Sorcerer**

- 🔮 High mana and spell damage

- ⚡ Proficient in using various spells

- 🧙‍♂️ Suitable for strategy players

#### Hero Attributes

- **Health (HP)**: Character's survivability

- **Mana (MP)**: Energy for casting spells

- **Strength (Strength)**: Affects physical attack damage

- **Dexterity**: Affects spell damage and accuracy

- **Agility**: Affects dodge chance

- **Gold**: Used to purchase equipment and items

- **Experience Points (XP)**: Experience required to level up

### 👹 Monster System

#### Monster Types

1. **Dragons**

- 🐉 Powerful attack and defense

- 🔥 Some possess special abilities

- 💎 Rich rewards upon defeat

2. **Spirits**

- 👻 High dodge rate, difficult to hit

- 🌙 Mysterious attack methods

- ⚡ High speed, high attack frequency

3. **Exoskeletons**

- 🦂 Balanced attribute configuration

- 🛡️ Moderate defense

- ⚔️ Suitable opponents for leveling up

### ⚔️ Battle System

#### Battle Flow

1. **Encounter a monster** → Enter battle state

2. **Choose an action**:

- 🗡️ **(A)ttack**: Attack with weapon

- ✨ **(S)spell**: Cast a spell

- 📊 **(I)nfo**: View details

- ⏭️ **(P)ass**: Skip turn

3. **Calculate damage**: Considers attack power, defense power, and evasion

4. **End of turn**: Character recovers a small amount of HP/MP

5. **Victory determination**: All enemies die = Victory

#### Damage Calculation

```
Physical Attack Damage = Base Damage + (Strength × 1.2) + Weapon Damage + Level Bonus

Spell Attack Damage = Base Spell Damage + (Agility × 0.5) + Level Bonus

Final Damage = max(Total Damage - Enemy Defense, Total Damage × 0.1)

```
## 🔧 Technical Implementation

### Core Design Patterns

- **Inheritance System**: → / `LivingEntity``Hero``Monster`

- **Factory Pattern**: Character and Monster Creation

- **Strategy Pattern**: Different Spell Effects

- **Combination Pattern**: Equipment System

### Key Algorithms

- **Damage Calculation Algorithm**: Balancing Game Difficulty

- **Dodge Mechanism**: Based on Agility Attribute

- **Experience System**: Level Up Mechanism

- **Random Generation**: Monster Encounters and Drops

## 🎮 Game Balance

### Combat Balance Adjustments

1. **Hero Attack Power**: `strength × 1.2 + weapon_damage + level_bonus`

2. **Monster Defense Power**: 30% of the original value, avoiding excessive defense

3. **Spell Damage**: `base_damage + (dexterity × 0.5) + level_bonus`

4. **Critical Hit Mechanism**: 20% Chance to deal double damage

### Numerical Reference

- **Level 1 Hero**: Approx. 800-1200 Attack Power

- **Level 1 Monster**: Approx. 60-120 Defense Power

- **Spell Damage**: 450-1000 Base Damage

- **Level Up Benefits**: +100 Health/Level, +10% Mana/Level

## 🙏 Acknowledgements
Thank you, Shuxun Zhou!

**Enjoy the game!** 🎮✨

## 📄 Output and running examples
``` 
Choose your hero:
[1] Warrior
[2] Sorcerer
[3] Paladin
Enter choice: 2
Welcome to Legends: Heroes and Monsters
W/A/S/D : Move
I       : Show hero info
Q       : Quit


=== Map ===
P . . X . M . M 
. M M X . . . M 
. . M X M . . . 
. . . . . X M . 
M . . . . M . . 
. M . . . X . M 
M X . M . M X . 
X X M . X M . . 
Command (W/A/S/D move, I info, Q quit): s

A battle begins!
⚔️ Battle begins! ⚔️

=== Battle Status ===
Heroes:
  Gandalf - HP:100/100 MP:120/120
Monsters:
  Firedrake - HP:100/100
===================

Gandalf's turn:
Choose action:
(A)ttack | (S)pell | (I)nfo | (P)ass
Your choice: a
Gandalf attacks Firedrake for 83 damage!

--- Monster Turn ---
Firedrake hits Gandalf for 57 damage!

=== Battle Status ===
Heroes:
  Gandalf - HP:53/100 MP:120/120
Monsters:
  Firedrake - HP:17/100
===================

Gandalf's turn:
Choose action:
(A)ttack | (S)pell | (I)nfo | (P)ass
Your choice: i

=== Detailed Battle Info ===
Your Heroes:
=== Gandalf ===
Level: 1 | XP: 0/10
HP: 53/100 | MP: 120/120
Strength: 30 | Dexterity: 50 | Agility: 30
Gold: 500
Weapon: None
Armor: None

Enemy Monsters:
Firedrake (Lvl 1, HP=17/100) [Dmg:50, Def:3, Dodge:0.1%]


--- Monster Turn ---
Firedrake hits Gandalf for 40 damage!

=== Battle Status ===
Heroes:
  Gandalf - HP:23/100 MP:120/120
Monsters:
  Firedrake - HP:17/100
===================

Gandalf's turn:
Choose action:
(A)ttack | (S)pell | (I)nfo | (P)ass
Your choice: s
Gandalf has no spells available!

--- Monster Turn ---
Firedrake hits Gandalf for 60 damage!

=== Battle Status ===
Heroes:
Monsters:
  Firedrake - HP:17/100
===================

💀 Defeat! The heroes have fallen... 💀
You died. Game over.
```


