import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import java.util.ArrayList;

import java.time.LocalTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class RestaurantTest {
  Restaurant restaurant;

  @BeforeEach
  public void setup() {
    LocalTime openingTime = LocalTime.parse("10:30:00");
    LocalTime closingTime = LocalTime.parse("22:00:00");
    restaurant = new Restaurant("Amelie's cafe", "Chennai", openingTime, closingTime);
    restaurant.addToMenu("Sweet corn soup", 119);
    restaurant.addToMenu("Vegetable lasagne", 269);
  }

  // >>>>>>>>>>>>>>>>>>>>>>>>>OPEN/CLOSED<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<
  // -------FOR THE 2 TESTS BELOW, YOU MAY USE THE CONCEPT OF MOCKING, IF YOU RUN INTO ANY TROUBLE
  @Test
  public void is_restaurant_open_should_return_true_if_time_is_between_opening_and_closing_time() {
    Restaurant spiedResRestaurant = Mockito.spy(restaurant);
    LocalTime time = LocalTime.parse("12:30:00");
    Mockito.when(spiedResRestaurant.getCurrentTime()).thenReturn(time);

    assertEquals(spiedResRestaurant.isRestaurantOpen(), true);
  }

  @Test
  public void is_restaurant_open_should_return_false_if_time_is_outside_opening_and_closing_time() {
    Restaurant spiedResRestaurant = Mockito.spy(restaurant);
    LocalTime time = LocalTime.parse("22:10:00");
    Mockito.when(spiedResRestaurant.getCurrentTime()).thenReturn(time);

    assertEquals(spiedResRestaurant.isRestaurantOpen(), false);
  }

  // <<<<<<<<<<<<<<<<<<<<<<<<<OPEN/CLOSED>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>

  // >>>>>>>>>>>>>>>>>>>>>>>>>>>MENU<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<
  @Test
  public void adding_item_to_menu_should_increase_menu_size_by_1() {
    int initialMenuSize = restaurant.getMenu().size();
    restaurant.addToMenu("Sizzling brownie", 319);
    assertEquals(initialMenuSize + 1, restaurant.getMenu().size());
  }

  @Test
  public void removing_item_from_menu_should_decrease_menu_size_by_1()
      throws itemNotFoundException {
    int initialMenuSize = restaurant.getMenu().size();
    restaurant.removeFromMenu("Vegetable lasagne");
    assertEquals(initialMenuSize - 1, restaurant.getMenu().size());
  }

  @Test
  public void removing_item_that_does_not_exist_should_throw_exception() {
    int initialMenuSize = restaurant.getMenu().size();
    assertThrows(itemNotFoundException.class, () -> restaurant.removeFromMenu("French fries"));
  }
  // <<<<<<<<<<<<<<<<<<<<<<<MENU>>>>>>>>>>>>>>>>>>>>>>>>>>>>>

  // >>>>>>>>>>>>>>>>>>>>>>>>>>>ORDER VALUE<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<
  @Test
  public void on_selecting_of_menu_items_should_return_order_value() {
    ArrayList<String> names = new ArrayList<String>();
    names.add("Sweet corn soup");
    names.add("Vegetable lasagne");

    assertEquals(restaurant.getOrderValue(names), 388);
  }
  // <<<<<<<<<<<<<<<<<<<<<<<ORDER VALUE>>>>>>>>>>>>>>>>>>>>>>>>>>>>

}
