package z03_tim_jdk8;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

public class OptionalExample {
	static class Person{
		private Debit debit;

		public Debit getDebit() {
			return debit;
		}

		public void setDebit(Debit debit) {
			this.debit = debit;
		}
		
	}
	
	static class Debit{
		private Card card;

		public Card getCard() {
			return card;
		}

		public void setCard(Card card) {
			this.card = card;
		}
		
	}
	
	static class Card{
		private Bank bank;

		public Bank getBank() {
			return bank;
		}

		public void setBank(Bank bank) {
			this.bank = bank;
		}
		
	}
	
	static class Bank{
		private String name;

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}
		
		
	}

	public static String getDebitBankName_1(Person person) {
		if (person.getDebit()!=null) {
			Debit debit = person.getDebit();
			if (debit!=null) {
				Card card = debit.getCard();
				if (card != null) {
					Bank bank = card.getBank();
					if (bank != null) {
						return bank.getName();
					}
				}
			}
			return null;
		}else {
			return null;
		}
	}
	
	public static String getDebitBankName_2(Person person) {
		return Optional.ofNullable(person.getDebit())
					.map(Debit::getCard)
					.map(Card::getBank)
					.map(Bank::getName)
					.orElse(null);
	}
	
	public static List<String> getDebitBankName_3(List<Person> person) {
		return person.stream()
			.map(Person::getDebit)
			.filter(Objects::nonNull)
			.map(Debit::getCard)
			.filter(Objects::nonNull)
			.map(Card::getBank)
			.filter(Objects::nonNull)
			.map(Bank::getName)
			.filter(Objects::nonNull)
			.collect(Collectors.toList());
		
	}
	
	
	public static void main(String[] args) {
		Person p1 = new Person();
		p1.debit = new Debit();
		p1.debit.card = new Card();
		p1.debit.card.bank = new Bank();
		p1.debit.card.bank.name = "ICBC";
		
		
		Person p2 = new Person();
		p2.debit = new Debit();
		p2.debit.card = new Card();
		
		Person p3 = new Person();
		p3.debit = new Debit();
		p3.debit.card = new Card();
		p3.debit.card.bank = new Bank();
		
		Person p4 = new Person();
		p4.debit = new Debit();
		p4.debit.card = new Card();
		p4.debit.card.bank = new Bank();
		p4.debit.card.bank.name = "ZSBC";
		
		Person p5 = new Person();
		
		
		List<Person> list = Arrays.asList(p1,p2,p3,p4, p5);
		list.stream().map(OptionalExample::getDebitBankName_1).forEach(System.out::println);
		System.out.println("");
		list.stream().map(OptionalExample::getDebitBankName_2).forEach(System.out::println);
		System.out.println(getDebitBankName_3(list));
		
	}
	
	
	
}
