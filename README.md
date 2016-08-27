# Minecraft JavaAPI

A simple Minecraft API based on the online Mojang API.

## Dependencies

This library uses the following ones :

* Google Gson ;
* Apache Commons Codec.

## Getting started

### Getting an instance

The API is designed to allow any implementation to be used. In order to guarantee forward compatibility, please work with the provided interfaces.
The provided implementation is the HTTP one :
```java
AccountRepository repository = new HttpAccountRepository();
```

### Getting an account

There are two ways to retrieve a Minecraft account :
```java
final Proxy proxy = Proxy.NO_PROXY;//The java.net.Proxy.
final String name = "Notch";//Here's your user name.
final String uuid = "[...]";//Here's your Mojang UUID.

try{
    //null if the account UUID was not found or if the UUID is invalid.
    MinecraftAccount someAccount = repository.getAccountByUuid(uuid, proxy);

    //null if the account name was not found.
    MinecraftAccount anotherAccount = repository.getAccountByName(name, proxy);
}
catch(AccountRepositoryException e){
    //Occurs when the Mojang accounts cannot be contacted or when the server emits an error.
    e.printStackTrace();
}
```
Please note that fetching accounts from the repository can sometimes throw an `AccountRepositoryException` because of a big
number of attempts in a short time (some minutes).

### Fetching the name history

The Mojang API provides a way to fetch the whole player name history. You can do that this way :
```java
try{
    //Let's fetch the history.
    List<NameRecord> history = repository.getNameHistory(uuid, proxy);

    if(history == null){
        System.out.println("The user with the specified UUID was not found.");
    }
    else{
        /*
         * Print it. The "changedToAt" attribute indicates when the player has changed his name for it.
         * Please note that we're using a "Long" value for it, so this value can be "null".
        */
        history.forEach(nameRecord -> System.out.printf("Name found : %s\n", nameRecord.getName()));
    }
}
catch(AccountRepositoryException e){
    //Occurs when the Mojang accounts cannot be contacted or when the server emits an error.
    e.printStackTrace();
}
```

### Getting account's properties

The "properties", is an object which basically contains named values.
Currently, theses properties are used by Mojang to store the user's textures. You can retrieve them this way :
```java
//Properties can be null ; this shouldn't occur, however.
MinecraftProperties properties = someAccount.getProperties();

//You can iterate on it :
properties.forEach(property -> System.out.printf("Property found : %s\n", property.getName()));

//Properties have different attributes :
Property prop = properties.getByName("someProperty");
System.out.printf("Name: %s, Value: %s, Signature: %s", prop.getName(), prop.getValue(), prop.getSignature());
```

### Playing with the textures property

The texture property has a wrapper class in order to play with them easily.
To do that, you must retrieve it with the `MinecraftProperties` class.

```java
//This object can be null ; this shouldn't occur, however.
MinecraftTextures textures = properties.getTexturesProperty();

//You can test if the player has a custom skin by checking if the URL is not null.
if(textures.getSkinDownloadUrl() == null){
    System.out.println("The player has no custom skin.");
}
else{
    System.out.println("The player has a custom skin!");
}

//Same for the cape.
System.out.println(textures.getCapeDownloadUrl() == null ? "The player has no cape." : "The player has a cape!");

//The skin and the cape are downloaded synchronously, so these methods should be called in another thread.
MinecraftSkin skin = textures.downloadSkin(proxy);//Never null, because a null skin is replaced by the default one.
MinecraftCape cape = textures.downloadCape(proxy);//Can be null if the user has no cape.
```

Basically, a Minecraft 1.8 skin is made of a standard skin plus an overlay.
The overlay functions are not supported when the skin is an older one, and returns null instead of the expected result.

The returned arms images does not have normalized sizes since the player can have a "slim" skin or a normal one.
You can retrieve these informations thanks to the object properties :

```java
System.out.println(skin.isSlim() ? "The player choosed the slim model" : "The player choosed the normal model");

System.out.println(skin.isAlex() ? "The player has a default Alex skin" : "The player has a default Steve skin");
```

Let's see an example on how to get the player face.

```java
//First of all we build a BufferedImage that would contain the final skin.
//We need to get the skin + the overlay (the head overlay is supported even with the older versions).

BufferedImage result = new BufferedImage(8, 8, BufferedImage.TYPE_INT_ARGB);

//We must write the skin and then the overlay.
Graphics2D graphics = result.createGraphics();
graphics.writeImage(skin.getHead(SkinOrientation.FRONT), 0, 0, null);
graphics.writeImage(skin.getHeadOverlay(SkinOrientation.FRONT), 0, 0, null);

Path savePath = Paths.get("/path/to/the/image.png");//You can use the NIO, NIO2 APIs if you want.

try(OutputStream out = new BufferedOutputStream(Files.newOutputStream(savePath))){
    ImageIO.write(result, "png", out);//Minecraft skins are PNG images, they have to support transparency.
}
catch(IOException e){
    e.printStackTrace();
}
```

You're now ready to use this API. Don't forget to give us your feedback, suggestions or improvements !
