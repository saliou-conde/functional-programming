package de.arag.functinal.programming.model;

import de.arag.functinal.programming.model.dto.Order;
import de.arag.functinal.programming.model.dto.OrderNameComparator;
import de.arag.functinal.programming.model.dto.OrderPriceComparator;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Collection;
import java.util.List;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

class CustomerTest {

    private final Logger log = LoggerFactory.getLogger(getClass());

    @Test
    void testEquals() {
        Customer c1 = new Customer();
        Customer c2 = new Customer();

        c1.setId(100);
        c1.setFirstname("Saliou");
        c1.setLastname("Conde");

        c2.setId(100);
        c2.setFirstname("Saliou");
        c2.setLastname("Conde");
        Customer c3 = c1;
        c3.setFirstname("Aliou");

        //Set<Customer> customers = Set.of(c1, c2);
        boolean isequal = c1 == c2;
        int hash1 = c1.hashCode();
        int hash2 = c2.hashCode();
        int hash3 = c3.hashCode();


        assertThat(isequal).isFalse();
        assertThat(c1.equals(c2)).isFalse();
        assertThat(hash1).isNotEqualTo(hash2);
        assertThat(c1 ).isSameAs(c3);
        assertThat(hash1).isEqualTo(hash3);
    }

    @Test
    void should_return_order_sum() {
        //Given
        double expected = 959.94;
        Customer c1 = Customer.builder()
                .firstname("Saliou")
                .lastname("Conde")
                .id(100)
                .orders(List.of(
                                Order.builder().price(159.99).name("Velo").id(UUID.randomUUID().toString()).build(),
                                Order.builder().price(149.99).name("Bicycle").id(UUID.randomUUID().toString()).build(),
                                Order.builder().price(159.99).name("Rad").id(UUID.randomUUID().toString()).build(),
                                Order.builder().price(139.99).name("Game of Thrones 1").id(UUID.randomUUID().toString()).build(),
                                Order.builder().price(169.99).name("Game of Thrones 2").id(UUID.randomUUID().toString()).build(),
                                Order.builder().price(179.99).name("Game of Thrones 3").id(UUID.randomUUID().toString()).build()
                        )
                )
                .build();

        //When
        Double actual = c1.getOrders().stream().map(Order::getPrice).reduce(0.0, Double::sum);

        //Then
        assertThat(actual).isEqualTo(expected);
    }

    @Test
    void should_return_order_sorted_by_name() {
        //Given
        var orders = new java.util.ArrayList<>(List.of(
                Order.builder().price(359.99).name("Velo").id(UUID.randomUUID().toString()).build(),
                Order.builder().price(449.99).name("Bicycle").id(UUID.randomUUID().toString()).build(),
                Order.builder().price(159.99).name("Rad").id(UUID.randomUUID().toString()).build(),
                Order.builder().price(139.99).name("Game of Thrones 1").id(UUID.randomUUID().toString()).build(),
                Order.builder().price(169.99).name("Game of Thrones 2").id(UUID.randomUUID().toString()).build(),
                Order.builder().price(179.99).name("Game of Thrones 3").id(UUID.randomUUID().toString()).build()
        ));
        log.info("###################################Before sorting by name###################################");
        orders.forEach(System.out::println);

        //When
        orders.sort(new OrderNameComparator());
        log.info("###################################After sorting by name###################################");
        orders.forEach(System.out::println);

        //Then
        assertThat(orders).hasSize(6);
    }

    @Test
    void should_return_order_sorted_by_price() {
        //Given
        var orders = new java.util.ArrayList<>(List.of(
                Order.builder().price(359.99).name("Velo").id(UUID.randomUUID().toString()).build(),
                Order.builder().price(449.99).name("Bicycle").id(UUID.randomUUID().toString()).build(),
                Order.builder().price(159.99).name("Rad").id(UUID.randomUUID().toString()).build(),
                Order.builder().price(139.99).name("Game of Thrones 1").id(UUID.randomUUID().toString()).build(),
                Order.builder().price(169.99).name("Game of Thrones 2").id(UUID.randomUUID().toString()).build(),
                Order.builder().price(179.99).name("Game of Thrones 3").id(UUID.randomUUID().toString()).build()
        ));
        log.info("###################################Before sorting by price###################################");
        orders.forEach(System.out::println);

        //When
        orders.sort( new OrderPriceComparator());
        log.info("###################################After sorting by price###################################");
        orders.forEach(System.out::println);

        //Then
        assertThat(orders).hasSize(6);
    }

    @Test
    void should_return_flat_mapped_order() {
        //Given
        Customer c1 = Customer.builder()
                .firstname("Saliou")
                .lastname("Conde")
                .id(100)
                .orders(List.of(
                                Order.builder().price(159.99).name("Velo").id(UUID.randomUUID().toString()).build(),
                                Order.builder().price(149.99).name("Bicycle").id(UUID.randomUUID().toString()).build(),
                                Order.builder().price(159.99).name("Rad").id(UUID.randomUUID().toString()).build(),
                                Order.builder().price(139.99).name("Game of Thrones 1").id(UUID.randomUUID().toString()).build(),
                                Order.builder().price(169.99).name("Game of Thrones 2").id(UUID.randomUUID().toString()).build(),
                                Order.builder().price(179.99).name("Game of Thrones 3").id(UUID.randomUUID().toString()).build()
                        )
                )
                .build();


        Customer c2 = Customer.builder()
                .firstname("Aliou")
                .lastname("Conde")
                .id(101)
                .orders(List.of(
                                Order.builder().price(159.99).name("Velo VTT").id(UUID.randomUUID().toString()).build(),
                                Order.builder().price(1490.99).name("Electron Bicycle").id(UUID.randomUUID().toString()).build(),
                                Order.builder().price(159.99).name("Electron Ride").id(UUID.randomUUID().toString()).build(),
                                Order.builder().price(139.99).name("Game of Thrones 1").id(UUID.randomUUID().toString()).build(),
                                Order.builder().price(169.99).name("Game of Thrones 2").id(UUID.randomUUID().toString()).build(),
                                Order.builder().price(179.99).name("Game of Thrones 3").id(UUID.randomUUID().toString()).build()
                        )
                )
                .build();

        List<Customer> customers = List.of(c1, c2);

        //When
        var flatMappedCustomers = customers.stream().flatMap(customer -> customer.getOrders().stream()).toList();

        List<List<Order>> mappedCustomersByOrders = customers.stream().map(Customer::getOrders).toList();
        var flatMappedCustomers2 = mappedCustomersByOrders.stream().flatMap(Collection::stream).toList();

        //Then
        assertThat(flatMappedCustomers2).isNotNull().hasSize(12);
        assertThat(mappedCustomersByOrders).hasSize(2);
        assertThat(flatMappedCustomers2).containsExactlyElementsOf(flatMappedCustomers);
    }

    @Test
    void should_return_flat_mapped_string() {
        //Given
        List<List<String>> employees = List.of(
                List.of("John Doe", "Jane Smith"), // HR Department
                List.of("Emily Davis", "Michael Brown"), // IR Department
                List.of("Chris Wilson", "Sarah Johnson") //Finance Department
        );

        //When
        var flatMappedEmployees = employees.stream().flatMap(List::stream).toList();
        var employeesByNameStartingWithJ = flatMappedEmployees.stream().filter(emp -> emp.startsWith("J")).toList();

        //Then
        Assertions.assertThat(flatMappedEmployees).hasSize(6);
        Assertions.assertThat(employeesByNameStartingWithJ).hasSize(2);
    }
}