///@formatter:off
/*
     hashbrown technique
    Copyright (C) 2015 Michael W. Fender

    This program is free software; you can redistribute it and/or modify
    it under the terms of the GNU General Public License as published by
    the Free Software Foundation; either version 2 of the License, or
    (at your option) any later version.

    This program is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
    GNU General Public License for more details.

    You should have received a copy of the GNU General Public License along
    with this program; if not, write to the Free Software Foundation, Inc.,
    51 Franklin Street, Fifth Floor, Boston, MA 02110-1301 USA.

 */
//@formatter:on

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
		switch(hashMethod){
		/*
		 * This would point to a hashing method. Java doesn't come with a variety of methods like PHP, so they would
		 * have to be implemented.
		 */
		}
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
