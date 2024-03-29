import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class RestaurantService {
  private static final List<Restaurant> restaurants = new ArrayList<>();

  public Restaurant findRestaurantByName(String restaurantName) throws restaurantNotFoundException {
    Restaurant searchResult =
        restaurants.stream()
            .filter(restaurant -> restaurantName.equals(restaurant.getName()))
            .findAny()
            .orElse(null);
    if (searchResult == null) throw new restaurantNotFoundException("Restaurant not found");
    else return searchResult;
  }

  public Restaurant addRestaurant(
      String name, String location, LocalTime openingTime, LocalTime closingTime) {
    Restaurant newRestaurant = new Restaurant(name, location, openingTime, closingTime);
    restaurants.add(newRestaurant);
    return newRestaurant;
  }

  public Restaurant removeRestaurant(String restaurantName) throws restaurantNotFoundException {
    Restaurant restaurantToBeRemoved = findRestaurantByName(restaurantName);
    restaurants.remove(restaurantToBeRemoved);
    return restaurantToBeRemoved;
  }

  public List<Restaurant> getRestaurants() {
    return restaurants;
  }
}
