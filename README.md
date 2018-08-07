# hashbrown
This project is intended to show the "hashbrown" technique. This technique allows for 1-way encryption that is not only difficult to break, but should one of the encryption methods used be broken, information can still be secure while new hash methods are implemented.

The set up uses a username:passphrase system.
Passphrases can actually be easier to remember than convoluted password schemes. While these schemes are created to making break hashes more difficult, it encourages people to do the worst thing possible for passwords: Record them. A written password can be stolen and a password manager program can be lost.
Salted hashes are handy as well, but it basically has a written password in the code itself. With the salt, cracking can be easier.

The hashbrown technique takes a unique username and creates a hash using one technique. The generated hash will be a string representing a hexadecimal number. From this, a series of other hashes will be determined, such as using the first character of that string, which will be a number from zero to f.
The passphrase is then split into its separate words. Using the hashes determined from the username, each word has its own hash created using different hash techniques.
Finally, one last hash is generated from all the hashes combined. This hash is saved for 1-way encryption.

## Entropy
There are 2 ways to consider the entropy of such a technique. Let's impose some limits for this calculation. First, only letters will be considered, so only [A-Za-z]. Second, each word must have 4-8 letters. Third, as stated for this example, 4 words will be used. Fourth, we'll use correctly spelled English words for now.

First, we can consider pure letter combinations. Since we have 4 words of 4-8 characters, calculating the raw entropy would be `log2(52^8-52^3)*4` which comes out to about 182 bits of entropy. 

However, we are also looking at using words. The key advantage of this is to make it easier for the user to remember, but at the cost of combinations. By using http://wordfinder.yourdictionary.com/letter-words/# and replacing # with 4, 5, 6, 7, and 8, we'll say we have 4,002 4-letter words, 8,887 5-letter words, 15,727 6-letter words, 23,958 7-letter words, and 29,718 8-letter words (as of 2015 April 25). Since these words can have upper or lower case characters, each word will have 2^letters uppercase/lowercase variations. Our formula is `log2(4002*2^4+8887*2^5+15727*2^6+23958*2^7+29718*2^8)*4`, or about 94 bits.

This is with the imposed limits. Naturally, allowing longer words would increase it. However, there will always be the human factor. The bits of entropy may be lower due to behavior patterns. Research would certainly need to be done for this, but such research would only be valuable to those seeking to break this method, thus public funding would likely be limited.

## Security Response
Brute force of this method, if using IBM's Blue Gene SuperComputer 9/04 (estimated at `70*10^12` unique password attempts/second) would take roughly 9.5 million years to generate a rainbow table. Reverse engineering of the username and passphrase would require breaking not just one hash method, but all of them and determining which hash methods were used. Because the hashes used vary from user to user, a break in one of the hash techniques will not reveal all techniques. Since different hash methods can return strings of differing lengths, a break in the final hash technique will not give simple clues to which hash methods were chosen. This would give developers time to phase out cracked techniques and keep their data secure.

## Social Impact
Passwords have achieved a measure of notoriety. They were designed for ease of use and security, but escalation of criminal activity caused adjustments to be made. Some adjustments, such as using longer passwords, made security stronger. Other adjustments, such as using special characters, made security weaker. Mathematically, it was more secure, but the required complexity would cause people to write down passwords. This made security breaches more common.

The problem in the design of these adjustments was ignoring human influence. The complexity would make computers struggle more, but humans are the ones who generally use these passwords. All they need is one.

However, looking at the passphrases, there will be resistance to them at first. Passphrases are change and general populations tends to bemoan change unless the reward is obvious and/or immediate. Implementing such a standard would require the implementor to apply it and let it run. Resistance fades with time and users will become accustom to it.

## xkcd and Waffle House
This technique was inspired by two sources. First is the webcomic xkcd, notably https://xkcd.com/936/. Second is Waffle House, which I worked at for about 7 years.

At Waffle House, as people will notice, they cook hashbrowns by first throwing a measure of shredded potatoes on the grill. They will then add toppings as ordered while the bottom is cooking. The hashbrowns are then flipped and cooked on the other side to cook the toppings. (Cheese is added at this time if ordered.) Finally, it's served up to the customer.

In the same manner, we take a username and hash it first (on the grill). We then get a collection of hashing techniques based on the username and apply them to the individual words of the passphrase (add the toppings). We then combine the hashes together (flipping the hashbrowns). Finally, we return a final hash of the concatenated hashes and serve it up.
