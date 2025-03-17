package de.ait.javalessons.service;

import de.ait.javalessons.model.BankAccount;
import de.ait.javalessons.repositories.BankAccountRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
public class BankAccountServiceTest {

    @Value("${bank.min-balance:0.0}")
    private double minBalance;

    @Autowired
    private BankAccountService bankAccountService;

    @Autowired
    private BankAccountRepository bankAccountRepository;

    private BankAccount testAccount;

    private List<BankAccount> testBankAccounts;

    @BeforeEach
    void setUp() {
        // Очищаем все существующие аккаунты
        bankAccountRepository.deleteAll();

        // Создаем тестовый аккаунт и сохраняем его
        testAccount = new BankAccount("TEST001", "Test User", 1000.0);
        testAccount = bankAccountService.saveNewBankAccount(testAccount);

        // Получаем обновленный список аккаунтов (теперь он не должен быть пустым)
        testBankAccounts = bankAccountService.getAllBankAccounts();
    }

    /* 1.***********************************get a list of all accounts****************************************** */
    @Test
    @DisplayName("Получение всех банковских аккаунтов")
    void getAllBankAccountsTest() {
        int sizeOfList = testBankAccounts.size();
        assertEquals(sizeOfList, bankAccountService.getAllBankAccounts().size());

        BankAccount account = bankAccountService.getAllBankAccounts().get(0);

        assertNotNull(account);
        assertEquals(testAccount.getId(), account.getId());
        assertEquals(testAccount.getAccountNumber(), account.getAccountNumber());
        assertEquals(testAccount.getOwnerName(), account.getOwnerName());
        assertEquals(testAccount.getBalance(), account.getBalance());
    }

    /* 2.***********************************find an account by its id****************************************** */
    @Test
    void findBankAccountByIdTestSuccessfully() {
        Optional<BankAccount> bankAccount = bankAccountService.findBankAccountById(testAccount.getId());
        assertTrue(bankAccount.isPresent());
        String accountNumber = bankAccount.get().getAccountNumber();
        assertEquals(testAccount.getAccountNumber(), accountNumber);
    }

    @Test
    void findBankAccountByIdTestFailed() {
        Long testId = 1000L;
        Optional<BankAccount> bankAccount = bankAccountService.findBankAccountById(testId);
        assertTrue(bankAccount.isEmpty());
    }

    /* 3.***********************************open an account****************************************** */
    @Test
    void saveNewBankAccount() {
        int oldSize = bankAccountService.getAllBankAccounts().size();
        BankAccount testAccountToSave = new BankAccount("1011", "Bob Neumann", 11500.0);

        BankAccount newBankAccount = bankAccountService.saveNewBankAccount(testAccountToSave);
        int newSize = bankAccountService.getAllBankAccounts().size();
        assertEquals(oldSize + 1, newSize);
        assertEquals(testAccountToSave.getAccountNumber(), newBankAccount.getAccountNumber());
    }

    /* 4.***********************************deposit amount into account****************************************** */
    @Test
    void deposit_shouldIncreaseBalance() {
        double initialBalance = testAccount.getBalance();
        double newBalance = bankAccountService.deposit(1000.0, testAccount.getId());

        assertEquals(initialBalance + 1000.0, newBalance);
    }

    @Test
    void deposit_shouldThrowIllegalArgumentException_whenAmountNotGreaterThan0() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
                () -> bankAccountService.deposit(-200.0, testAccount.getId()));
        assertEquals("Amount must be greater than 0", exception.getMessage());
    }

    /* 5.***********************************withdrawal from account****************************************** */
    @Test
    void withdraw_shouldDecreaseBalance() {
        double initialBalance = testAccount.getBalance();
        // Убедимся, что мы не пытаемся снять слишком много
        double amountToWithdraw = Math.min(500.0, initialBalance - minBalance);

        double newBalance = bankAccountService.withdraw(amountToWithdraw, testAccount.getId());

        assertEquals(initialBalance - amountToWithdraw, newBalance);
    }

    @Test
    void withdtaw_shouldThrowIllegalArgumentException_WhenBankAccountNotFound() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
                () -> bankAccountService.withdraw(300.0, 1000L));
        assertEquals("Bank account with id 1000 not found", exception.getMessage());
    }

    //Нехватка средств
    @Test
    void withdraw_shouldThrowIllegalArgumentException_whenInsufficientFunds() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
                () -> bankAccountService.withdraw(testAccount.getBalance() + 10.0, testAccount.getId()));
        assertEquals("Amount is greater than the current balance", exception.getMessage());
    }

    @Test
    void withdraw_shouldThrowIllegalArgumentException_whenAmountNotGreaterThan0() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
                () -> bankAccountService.withdraw(-200.0, testAccount.getId()));
        assertEquals("Amount must be greater than 0", exception.getMessage());
    }

    @Test
    void withdraw_shouldThrowIllegalArgumentException_whenAmountGreaterThanBalance() {
        // Динамическая проверка с использованием текущего баланса
        double tooMuchAmount = testAccount.getBalance() + 1.0;
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
                () -> bankAccountService.withdraw(tooMuchAmount, testAccount.getId()));
        assertEquals("Amount is greater than the current balance", exception.getMessage());
    }

    @Test
    void withdraw_shouldThrowIllegalArgumentException_WhenTheAmountLeftOnTheAccountWillBeLessThanTheAllowedAmount() {
        // Создаем аккаунт с достаточным балансом для проведения теста
        BankAccount specialAccount = new BankAccount("TEST123", "Test User", minBalance + 100.0);
        BankAccount savedAccount = bankAccountService.saveNewBankAccount(specialAccount);

        Double tooBigAmount = savedAccount.getBalance() - minBalance + 1.0;
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
                () -> bankAccountService.withdraw(tooBigAmount, savedAccount.getId()));
        assertEquals("The current balance is less than the minimum balance", exception.getMessage());
    }
}