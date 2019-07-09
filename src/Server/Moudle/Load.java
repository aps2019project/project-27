package Server.Moudle;


import com.gilecode.yagson.YaGson;
import com.gilecode.yagson.YaGsonBuilder;

import java.io.*;
import java.lang.reflect.Type;
import java.util.ArrayList;
public class Load {
	public static void saveAccount() throws IOException {
		YaGson gson = CreatGson.getGson ();
		AccountTmp accountTmp= new AccountTmp ();
		accountTmp.accounts=Account.getAccounts ();
		FileWriter writer = new FileWriter( "Accounts.json" );
		String obj = gson.toJson ( accountTmp );
		writer.write ( obj );
		int a=1;
		System.out.println ( "saved!\n" );
		writer.close();
	}
	public static void saveMAndH() throws IOException {
		YaGson gson = CreatGson.getGson ();
		MinionAndHeroTmp minionAndHeroTmp = new MinionAndHeroTmp ();
		minionAndHeroTmp.minionAndHeroes=MinionAndHero.getMinionAndHeroes ();
		FileWriter writer = new FileWriter( "MinionAndHeroes.json" );
		String obj = gson.toJson ( minionAndHeroTmp );
		writer.write ( obj );
		int a=1;
		System.out.println ( "saved!\n" );
		writer.close();
	}
	public static void loadMinionAndHeros() throws FileNotFoundException {
		YaGson gson = CreatGson.getGson ();
		Reader reader = new FileReader( "MinionAndHeroes.json" );
		MinionAndHeroTmp tmp = gson.fromJson ( reader, MinionAndHeroTmp.class);
		MinionAndHero.setMinionAndHeroes ( tmp.minionAndHeroes );
	}
	public static void loadSpells() throws FileNotFoundException {
		YaGson gson = CreatGson.getGson ();
		Reader reader = new FileReader ( "Spells.json");
		SpellTmp tmp = gson.fromJson ( reader, ( Type ) SpellTmp.class);
		Spell.setSpells ( tmp.spells );
	}
	public static void loadAccounts() throws FileNotFoundException {
		YaGson gson = CreatGson.getGson ();
		Reader reader = new FileReader ( "Accounts.json" );
		AccountTmp tmp = gson.fromJson ( reader, AccountTmp.class );
		Account.setAccounts (tmp.accounts);
	}
	public static void loadItems() throws FileNotFoundException {
		YaGson gson = CreatGson.getGson ();
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
	private static YaGson gson;
	public static YaGson getGson (){
		if ( gson == null ){
			YaGsonBuilder builder = new YaGsonBuilder ();
			gson = builder.create ();
		}
		return gson;
	}
}