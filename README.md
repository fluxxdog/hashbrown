# hashbrown
This project is intended to show the "hashbrown" technique. This technique allows for 1-way encryption that is not only difficult to break, but should one of the encyrption methods used be broken, information can still be secure while new hash methods are implemented.

The set up uses a username:passphrase system. The passphrase used in this example uses a 4-word phrase.
Passphrases can actually be easier to remember than convulted password schemes. While these schemes are created to making break hashes more difficult, it encourages people to do the worst thing possible for passwords: Record them. A written password can be stolen and a password manager program can be lost.
Salted hashes are handy as well, but it basically has a written password in the code itself. With the salt, cracking can be easier.

The hashbrown technique takes a unique username and creates a hash using one technique. The generated hash with be a string representing a headecimal number. From this, a series of other hashes will be determined, such as using the first character of that string, which will be a number from 0x0 to 0xf.
The passphrase is then split into its separate words, in this example, 4. Using the hashes determined from the username, each word has its own hash created using different hash techniques.
Finally, one last hash is genereated from all the hashes combined.

Because the hashes used vary from user to user, a break in one of the hash techniques will not reveal all techniques. Since hashes can return strings of differing lengths, a break in the final hash technique will not give simple clues to which hash methods were chosen. This would give developers time to phase out cracked techniques and keep their data secure.


