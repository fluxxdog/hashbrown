public class HashBrown{

	private static final String[]	hasharray	= {"whirlpool", "sha512"};

	HashBrown(){}

	public static String served(final String username, final String passphrase){
		String cooking= hash(hasharray[0], username);
		final String[] toppings= hashingMethod(cooking);
		final String[] words= passphrase.split(" ");
		for(int i= 0; i < 4; i++){
			cooking+= hash(toppings[i], words[i]);
		}
		return hash(hasharray[1], cooking);
	}

	private static String hash(final String hashMethod, final String toBeHashed){
		/*
		 * This would point to a hashing method. Java doesn't come with a variety of methods like PHP, so they would
		 * have to be implemented.
		 */
		return null;
	}

	private static String[] hashingMethod(final String userhash){
		switch(userhash.charAt(0)){
		// This is provided as an example only. Developers are encouraged to use a collection of different hash
		// methods
		default:
			// This assumes a 4-word passphrase system
			return new String[] {"tiger192,4", "sha512", "haval192,4", "ripemd256"};
		}
	}
}
