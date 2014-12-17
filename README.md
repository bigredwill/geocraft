GeoCraft
========

CraftBukkit 1.9 Minecraft Plugin for cs185c, Virtual Environments, Fall 2014

========
commands:

    whereami: 

      description: What is your real life position?


    setkeypoint: <x> <y> <z> <lat> <lng>

      description: Set the keypoint for GeoCraft


    setscale: <scale> 

      ( 1 Minecraft Block ) * scale = 1 Earth meter

      description: Set the scale for GeoCraft


    getdefaults:

      description: Get the set defaults for GeoCraft



To test it out, simply put GeoCraft.jar in to your CraftBukkit server's plugins folder.

Once in game, you need to /setkeypoint and /setscale.

After that, /whereami will tell you your Geocoded location.