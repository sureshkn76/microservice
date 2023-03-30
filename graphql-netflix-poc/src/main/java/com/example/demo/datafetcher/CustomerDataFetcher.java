package com.example.demo.datafetcher;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;

import com.example.demo.model.Account;
import com.example.demo.model.AccountInput;
import com.example.demo.model.Customer;
import com.example.demo.model.CustomerInput;
import com.example.demo.repo.AccountRepository;
import com.example.demo.repo.CustomerRepository;
import com.netflix.graphql.dgs.DgsComponent;
import com.netflix.graphql.dgs.DgsData;
import com.netflix.graphql.dgs.DgsDataFetchingEnvironment;
import com.netflix.graphql.dgs.DgsMutation;

/**
 * GraphQL Terminology Schema: is a GraphQL schema which includes Query and
 * Mutation Query: is a read operation requested to a GraphQL Server. Mutation:
 * is a create, update and delete operations requested to GraphQL Server.
 * Resolver: is responsible for mapping the Query (or Mutation) operation to
 * backend service responsible to handle the request. Fields: are the properties
 * of the GraphQL objects for e.g. User, Post and Comment etc. Scalar: is the
 * type of the field for e.g. Int, Float, String, Boolean, ID. ID represents
 * unique identifier, serialized as String. Enum: is a special kind of Scalar
 * that is restricted to a particular set of allowed values. Interface: is an
 * abstract type that includes a certain set of fields that a type must include
 * to implement the interface. Input: is a GraphQL object passed, which is
 * passed in Mutation operation such as create and update.
 **/

//@DgsComponent — It acts like @RestController annotation
@DgsComponent
public class CustomerDataFetcher {

	@Autowired
	CustomerRepository customerRepository;

	@Autowired
	AccountRepository accountRepository;

	/**
	 * GraphQL Query with @DgsQuery and @DgsData annotation.
	 * 
	 * @DgsQuery for Root Queries @DgsMutation for Root Mutations, and @DgsData for
	 *           Nested Queries Its a method level annotation used to make the
	 *           method as a datafetcher. It has two parameters — “parentType” and
	 *           “field”. parentType — its a type that contains field. field —
	 *           indicates the field the datafetcher is responsible for. Point to
	 *           note here, parameter ‘field’ is an optional parameter which will be
	 *           derived from method name. We can’t define @DgsQuery
	 *           and @DgsMutation in a single method, instead we can use @DgsData
	 *           with parentType as ‘Query’ or ‘Mutation’. To pass an input
	 *           parameter we should annotate the method argument
	 *           with @InputArgument
	 **/
	@DgsData(parentType = "Query", field = "customers")
	public List<Customer> customers() {
		return customerRepository.findAll();
	}

	@DgsData(parentType = "Query", field = "customerById")
	public Customer customerById(String customerId) {
		return Optional.ofNullable(customerRepository.findById(customerId)).get().orElse(null);
	}

	@DgsData(parentType = "Customer", field = "accounts")
	public List<Account> accounts(DgsDataFetchingEnvironment dgsDataFetchingEnvironment) {
		Customer customer = dgsDataFetchingEnvironment.getSource();
		List<Account> accountList = new ArrayList<>();
		for (Account account : customer.getAccounts()) {
			Account accountResponse = accountRepository.findById(account.getAccountId()).get();
			accountList.add(accountResponse);
		}
		return accountList;
	}

//	@DgsMutation — same as ‘@PostMapping, @PutMapping, @DeleteMapping’ annotation    
	@DgsMutation
	public Customer createCustomer(CustomerInput customerInput) {
		Customer customer = Customer.builder().contact(customerInput.getContact()).name(customerInput.getName())
				.gender(customerInput.getGender()).mail(customerInput.getMail())
				.accounts(mapCustomerAccounts(customerInput.getAccounts())).build();
		Customer customerResponse = customerRepository.save(customer);
		return customerResponse;
	}

	private List<Account> mapCustomerAccounts(List<AccountInput> accountIpnut) {
		List<Account> accountsList = accountIpnut.stream().map(accInput -> {
			Account account = Account.builder().accountBalance(accInput.getAccountBalance())
					.accountNumber(accInput.getAccountNumber()).accountStatus(accInput.getAccountStatus()).build();
			return account;
		}).collect(Collectors.toList());
		return accountsList;
	}

	@DgsMutation
	public Customer updateCustomer(CustomerInput customerInput) {
		Customer customer = Customer.builder().contact(customerInput.getContact()).name(customerInput.getName())
				.gender(customerInput.getGender()).mail(customerInput.getMail())
				.accounts(mapCustomerAccounts(customerInput.getAccounts())).build();
		Customer customerResponse = customerRepository.save(customer);
		return customerResponse;	}

	@DgsMutation
	public boolean deleteCustomer(String id) {
		 customerRepository.deleteById(id);
		 return true;
	}

}
