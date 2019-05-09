package Moudle;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.*;
import java.lang.reflect.Type;
import java.util.ArrayList;
public class Load {
	public static void saveAccount() throws IOException {
		Gson gson = CreatGson.getGson ();
		AccountTmp accountTmp= new AccountTmp ();
		accountTmp.accounts=Account.getAccounts ();
		FileWriter writer = new FileWriter( "Accounts.json" );
		gson.toJson ( accountTmp,writer );
		System.out.println ( "saved!\n" );
		writer.close();
		int a=1;
	}
	public static void loadMinionAndHeros() throws FileNotFoundException {
		Gson gson = CreatGson.getGson ();
		Reader reader = new FileReader( "MinionAndHeroes.json" );
		MinionAndHeroTmp tmp = gson.fromJson ( reader, MinionAndHeroTmp.class);
		MinionAndHero.setMinionAndHeroes ( tmp.minionAndHeroes );
	}
	public static void loadSpells() throws FileNotFoundException {
		Gson gson = CreatGson.getGson ();
		Reader reader = new FileReader ( "Spells.json");
		SpellTmp tmp = gson.fromJson ( reader, ( Type ) SpellTmp.class);
		Spell.setSpells ( tmp.spells );
	}
	public static void loadAccounts() throws FileNotFoundException {
		Gson gson = CreatGson.getGson ();
		Reader reader = new FileReader ( "Accounts.json" );
		AccountTmp tmp = gson.fromJson ( reader, AccountTmp.class );
		Account.setAccounts (tmp.accounts);
	}
	public static void loadItems() throws FileNotFoundException {
		Gson gson = CreatGson.getGson ();
		Reader reader = new FileReader ( "Items.json" );
		ItemTmp tmp = gson.fromJson ( reader, ItemTmp.class );
		Item.setItems(tmp.items);
	}
}
class ItemTmp{
	ArrayList<Item> items;
}
class MinionAndHeroTmp {
	ArrayList<MinionAndHero> minionAndHeroes;
}
class SpellTmp {
	ArrayList<Spell> spells;
}
class AccountTmp {
	ArrayList<Account> accounts;
}
class CreatGson {
	private static Gson gson;
	public static Gson getGson (){
		if ( gson == null ){
			GsonBuilder builder = new GsonBuilder ();
			gson = builder.create ();
		}
		return gson;
	}
}