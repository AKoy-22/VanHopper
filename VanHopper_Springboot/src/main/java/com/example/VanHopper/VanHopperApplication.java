package com.example.VanHopper;

import java.util.ArrayList;
import java.util.List;

import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.example.VanHopper.model.Event;
import com.example.VanHopper.model.EventRepository;
import com.example.VanHopper.model.Registration;
import com.example.VanHopper.model.RegistrationRepository;
import com.example.VanHopper.model.User;
import com.example.VanHopper.model.UserRepository;



@SpringBootApplication
public class VanHopperApplication {

	public static void main(String[] args) {
		SpringApplication.run(VanHopperApplication.class, args);
	}
	@Bean
	ApplicationRunner init(EventRepository eventRepo,UserRepository userRepo,RegistrationRepository registrationRepo) 
	{
		return args -> 
		{
			
			
			/****************************************Create User Repo********************************************************************************/
			ArrayList<User> users=new ArrayList<>();
			users.add(new User("Admin","admin","admin@gmail.com","9999543210","p999"));
			users.add(new User("E2345","Akiko","a@gmail.com","9876543210","p123"));
			users.add(new User("E3256","Puru","p@gmail.com","8765432190","p456"));
			users.add(new User("E1234","Shrishty","s@gmail.com","1234567890","p789"));
			users.add(new User("E4567","Vane","v@gmail.com","6543217890", "p987"));
	
			userRepo.saveAll(users);
			userRepo.findAll().forEach(System.out::println);
			
			/****************************************Create Event Repo********************************************************************************/
			ArrayList<Event> events = new ArrayList<>();
		    events.add(new Event("Jokes Please! - Live Stand-Up Comedy","The Cambrian Hall", "03-31-23", "Comedy", List.of("Jokes Please!", "The Debaters"),"https://www.eventbrite.ca/e/jokes-please-saturdays-in-kits-live-stand-up-comedy-tickets-462003453767?keep_tld=1", "East Vancouver"));
			events.add(new Event("How Psilocybin Mushrooms Can Help Save The World","Vancouver Convention Centre West","03-31-23", "Health & Lifestyle", List.of("JTallGrass Natural health Ltd", "Nancy Grosman"),"https://www.eventbrite.ca/e/how-psilocybin-mushrooms-can-help-save-the-world-tickets-528614007727?aff=ebdssbdestsearch", "New Westminster"));
			events.add(new Event("Dance Dance Dance: Pop-Up Latin Dance Classes","Vancouver Civic Theatres","03-24-23","Performing and Visual arts", List.of("Vancouver Civic Theatres", "Social dancing at the Annex"),"https://www.eventbrite.ca/e/dance-dance-dance-pop-up-latin-dance-classes-tickets-511136642457?aff=erelpanelorg&keep_tld=1", "Brentwood"));
			events.add(new Event("Ryan Shaw","Orpheum","04-28-23","Music", List.of("VSO", "VSO Pops"),"https://www.vancouversymphony.ca/event/aretha-a-tribute/", "Downtown"));
			events.add(new Event("As We Rise: Photography","The Polygon Gallery","04-14-23","Performing and Visual arts", List.of("Gordon Parks", "Malick Sidibé"),"https://thepolygon.ca/exhibition/as-we-rise/", "North Vancouver"));
			events.add(new Event("Brentwood Earth Day","Memorial Peace Park","04-22-23","Health & Lifestyle", List.of("Peter Parks", "Sandra Wu"),"https://www.destinationvancouver.com/event/maple-ridge-earth-day/19667/", "Brentwood"));
			events.add(new Event("Dressed for History: Why Costume Collections Matter","Museum of Vancouver","04-16-23","Family", List.of("Ivan Sayers", "Claus Jahnke", "Melanie Talkington"),"https://museumofvancouver.ca/dressed-for-history", "Downtown"));
			events.add(new Event("Lamondance","The Dance Centre","04-20-23","Performing and Visual arts", List.of("Davi Rodrigues", "Jera Wolfe"),"https://thedancecentre.ca/event/lamondance-discover-dance/", "Downtown"));
			events.add(new Event("North Van Arts CityScape Date Nights","Maplewood House","04-06-23","Performing and Visual arts	", List.of("Jinfei Wang", "Surnoor Singh"),"https://northvanarts.ca/education/cityscape-date-nights-first-thursdays-feb-2-jun-1/", "North Vancouver"));

			eventRepo.saveAll(events);
			eventRepo.findAll().forEach(System.out::println);
			
			/****************************************Registering the one user to many Events********************************************************************************/
			ArrayList<Registration> register=new ArrayList<>();
    
			  //first user register to first event
			  register.add(new Registration(users.get(1),events.get(0)));
			  //first user register to second event
			  register.add(new Registration(users.get(1),events.get(1)));
			  //first user register to third event
			  register.add(new Registration(users.get(1),events.get(2)));
			
			  // 2nd user register to first event
			  register.add(new Registration(users.get(2),events.get(0)));
			  registrationRepo.saveAll(register);
			  registrationRepo.findAll().forEach(System.out::println);
			
	      };
	}
}
