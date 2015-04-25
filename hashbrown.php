<?php

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
