<?php
//@formatter:off
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
// @formatter:on
namespace encryption;

final class HashBrown{
	public function __construct(){}
	public function __clone(){}
	private static final $hasharray= array ('user'=>'whirlpool', 'final'=>'sha512' );
	private static function hashingMethod($userhash){
		switch(substr($username, 0, 1)){
		// This is provided as an example only. Developers are encouraged to use a collection of different hash methods
		default:
			// This assumes a 4-word passphrase system
			return array ('tiger192,4', 'sha512', 'haval192,4', 'ripemd256' );
		}
	}
	public static function served($username, $passphrase){
		$cooking= hash(self::$hasharray['username'], $username);
		$toppings= self::hashingMethod($userhash);
		foreach(explode("\s", $passphrase) as $k=>$v){
			$cooking.= hash($toppings[$k], $v);
		}
		return hash(self::$hasharray['final'], $cooking);
	}
}
?>
