<h1>TextGenerator</h1>

[![IMAGE ALT TEXT HERE](https://img.youtube.com/vi/QFX4GHEk6OQ/0.jpg)](https://www.youtube.com/watch?v=QFX4GHEk6OQ)
<h2>Features</h2>
<ul>
<li>Drag to move the text</li>
<li>You can edit the text whenever you want</li>
<li>GUI to edit everything of the text</li>
<li>A for Minecraft specific font size with slabs and stairs to generate small texts</li>
<li>\n for a new line</li>
<li>The text cannot override other blocks</li>
</ul>

<h2>Build instructions</h2>

```./mvnw clean package```

<p>The output .jar will be in target/</p>

<h2>Examples of texts</h2>
<img src="https://cdn.modrinth.com/data/KR9zve3W/images/c1c4155f1f91d9aba6e9c7d0b7122c8678ffbadc.png"  alt="" width="400" height="250"/>
<img src="https://cdn.modrinth.com/data/KR9zve3W/images/13911893531aa7abb54cd5d446e26d9422b8da7e.png"  alt="" width="400" height="250"/>
<img src="https://cdn.modrinth.com/data/KR9zve3W/images/6bced5cc1aa3f86538870677cef70b0bd2a22d5d.png"  alt="" width="400" height="250"/>
<img src="https://cdn.modrinth.com/data/KR9zve3W/images/08a702a06177475e0f4b384a832069042d602421.png"  alt="" width="400" height="250"/>
<h2>Commands</h2>
<h3>When not editing a text:</h3>
<p>/tg generate [Your text] - Generates a text with your text settings in front of the player</p>
<p>/tg edit this - When looking at a previous generated text the player goes into the editing mode to edit it</p>
<p>/tg settings - Opens a GUI to edit the default text settings for the next generate</p>
<h3>When editing a text:</h3>
<p>/tg confirm - Stops editing the text and leaves it there</p>
<p>/tg edit menu - Opens a GUI to edit the text</p>
<p>/tg move [coordinates] - When drag to move is disabled it moves the text to the given directions. First number indicates left/right, second indicates top/bottom, and third indicates back/front</p>
<p>/tg refresh - When drag to move is disabled it moves the text to the players looking direction</p>
<p>/tg cancel - Destroys the text (when it was previously generated) or sets the text to its previous state (when it was edited)</p>
<p>/tg destroy - Destroys the text</p>
<p>/tg reset - Destroys all build blocks and builds it again</p>
<p>/tg remove - Removes the text from the plugin (the text cannot be edited anymore)</p>
<p>/tg setText [Your text] - Changes the text </p>

<h2>PlaceHolderAPI</h2>
<p>%textgeneratorGeneratedTexts% - Returns a list of all texts ever generated</p>

<h2>The configuration file </h2>
<pre>
placementRange: 30
dragToMove: false
textSettings:
  block: quartz_block
  fontName: Arial
  fontSize: 15
  fontStyle: BOLD
  underline: false
  lineSpacing: 2</pre>
