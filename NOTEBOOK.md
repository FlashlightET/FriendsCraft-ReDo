# KF MOD

## Main plans
- Target 1.21 for now, may backport eventually

- Aim is to make a better, balanced alternative to kemonocraft 

- additionally, also have more friends than KC

- will be able to be expanded with addon mods?

- friends will be more resemblant of players instead of being like a foot tall
  - if serval the friend would realistically be about 5.4-5.6 feet tall IRL (guesstimate) why make her freaking tiny in the mod
  - The serval model i already made (minus the ears) is 30 pixels tall (player is 32 pixels tall)
  - Friends will be sized accurately to each other too (they are usually about the same size officially other than some smaller ones)
- Friends will revert when killed
  - Either only supported friends, or I'd have to make all the animals as mobs too which would be weird as they would otherwise not naturally spawn
- Sandstar splash potion
  - Throwing at a supported animal will make the animal a friend
  - INTERESTING IDEA: Friends can be updated from the internet
    - If a friend is not in the version of the mod you have, data from newer versions can be copied to a friends directory in .minecraft
    - should work aside from any weapons they might spawn with that arent already available
      - This will be accomplished by having friend models, skins, base stats, types, and spawn conditions as json/other files
      - They would already be like that regardless, but the mod may be able to read a folder to load in new friends
      - This can also lead to non-friend characters running on the friend engine, which may be funny



## SPAWNING MECHANICS
- Naturally spawning friends will despawn when away from the player
- They also will not spawn if there is another one of that species in the spawning radius
- If a friend is tamed, that friend will not naturally spawn again unless the tamed one "dies"
- Structure spawns will take priority - it will make sure the structures' designated friends will attempt to spawn before the random ones.


## FRIEND LIST (by biome)

main plans, other ones may be added on the side these are just in mind

Friends grouped with a comma will tend to spawn together and follow each other until tamed

Friends may possibly be able to spawn on their own too?

- Serval, Caracal (SAVANNA)
- Bat Eared Fox (SAVANNA)
- Kit Fox (SAVANNA, DESERT)
- Roadrunner (DESERT, BADLANDS)
- Raccoon, Fennec (FORESTS, DESERTS)
- Red Fox (FORESTS, TAIGA)
- Arctic Fox (COLD PLACE)
- Toki (MOUNTAINS)
- Scarlet Ibis (MOUNTAINS)
- Alpaca Suri (MOUNTAINS)
- Ezo Red Fox, Silver Fox (HOT SPRINGS STRUCTURE in COLD MOUNTAIN BIOMES)
- ppp penguins (PPP STAGE STRUCTURE)
- African Penguin (BEACH, not SNOWY BEACH though)
- Gray Fox (PLAINS, SAVANNA, FOREST)
- Gray Wolf (FOREST, TAIGA)
- Margay (JUNGLE, PPP STAGE STRUCTURE)
- Capybara (HOT SPRINGS STRUCTURE, SWAMPS)
- Ocelot (JUNGLE)
- Cat (VILLAGE)
  - only comes in tan tabby as thats the only official domestic cat design
- Dog (VILLAGE, except desert)
  - Comes in three breeds (mutt, shiba, husky), all considered separate friends however only one will spawn at once.
    - Basically, these friends' spawn code will skip the check for ownership and only check if there's already untamed ones in the spawn range.
    - What will actually happen is it will check for ownership, and simply remove that friend from the dog pool if already tamed.
- Lophorina, Parotia (JUNGLE)
- GBOP (JUNGLE)
  - May spawn with above entry group or alone




## STRUCTURES
- PPP Stage (found in all 3 types of beach)
  - Royal Penguin
  - Emperor Penguin
  - Gentoo Penguin
  - Rockhopper Penguin
  - Humboldt Penguin
 
- Hot springs (found in cold mountains biome)
  - Ezo
  - Silver
  - Capybara (this will be unlikely to conflict as capybara's spawn biomes will not generate anywhere near this)



## GENERIC FRIEND ABILITIES
- All friends will severely try to avoid fire
  - Mainly a reference to 1x07

- Red Fox species friends (red fox, ezo red fox, silver fox, arctic fox esp.) can walk on powder snow
  - so can other super cold area inclusive friends

- Serval-related friends (Serval, Caracal) have increased jump height
  - Can still jump normally (this is mainly for attacks or grabbing things in the air or jumping on top of trees?)
  - Smaller cat friends (like housecat, ocelot) also have this, but to a lesser extent

- Penguin friends can swim underwater for extended periods
  - Emperor penguin will swim in freezing water too, other penguins will not spend too long in cold water

- FRIENDS WITH WEAPONS
  - Spawn with the weapon.

- CAT FRIENDS IN GENERAL
  - Scare away creepers
  - Barehanded attack is stronger than other friends' barehanded attacks
    - Does not stack with weapons as that does not make sense
      - However, if a melee weapon is used during a jumping attack, it will be slightly more effective due to the falling speed

- BIRD FRIENDS IN GENERAL
  - can fly.
  - do not have "flying bars" they just can fly
  - idle flying most of the time
  - either fly upright at normal walking speed or fly in a diving motion at faster speeds
 
- PEREGRINE FALCON, if i add her
  - flies really fast and in a particular way
    - possibly based on elytra physics but faster

- RACCOON
  - washes her hands
    - she did this in kemonocraft too

- SERVAL
  - fucks with redstone contraptions
    - this was already implemented in the serval from the mcreator group mod collab
    - you may want to sit her down if you plan on working on redstone
    - you can craft instructions that you can read to her that tell her not to fuck with redstone contraptions
  
  
  

## LUCKY BEAST
- Shows information about the friends
- Makes japari buns

## Friend Data
- Common Entity Tags

- Common Mob Tags

- Inventory [ List, x18 ]

- Slot of Held Item [ Int ]
  - The mob will switch to the necessary item when needed
  - such as holding the food when eating it or holding sword when attacking
  - Cannot perform item actions without holding the item
 
- "Heat" for special attacks
  - think of it more like an MP type thing
  - name inspired by heat actions from yakuza
    - perhaps i could make them perform heat actions if they have a special item? would be funny. eg if theyre holding a hoe they acn do the hoe heat action(??)
  - Friend gets stronger up to about 1.2x?
  - Drains when hurt (instant -x) or not attacking (steady decrease)
  - Rises when attacking (instant +x)

- Whether the friend can walk on powder snow
  - important! because friends not from snowy cold areas should not be able to walk on powder snow unequipped.
    - if in the inventory, they can use shovels to dig out though. leather boots also work as they do on players.
      - They can equip leather boots on their own if theyre within range of powder snow blocks

## JOTTING THINGS DOWN
- i would like to make the friends their own class, FriendEntity, so that I can extend *that* as the base for the friend mobs.



