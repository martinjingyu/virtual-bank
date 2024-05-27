package Test.TestEntity.Message;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import Entity.*;
import static org.junit.jupiter.api.Assertions.*;

class AgentTest {
    private Agent agent;
    private AccountManager accountManager;

    @BeforeEach
    void setUp() {
        agent = new Agent(new Kids());
    }

    @Test
    void receiveUserInput_Cancel() {
        assertEquals("Operation cancelled. Please enter a new command.", agent.receiveUserInput("cancel"));
    }

    @Test
    void receiveUserInput_InvalidCommand() {
        assertEquals("Invalid command. Please say 'delete account' or 'modify account name'.", agent.receiveUserInput("random"));
    }

    @Test
    void receiveUserInput_DeleteAccount() {
        assertEquals("Please specify the type of account to delete: 'saving' or 'current'.", agent.receiveUserInput("delete account"));
    }

    @Test
    void receiveUserInput_ModifyAccountName() {
        assertEquals("Please specify the type of account to modify: 'saving' or 'current'.", agent.receiveUserInput("modify account name"));
    }

    @Test
    void receiveUserInput_SelectAccountType_InvalidType() {
        agent.receiveUserInput("delete account");
        assertEquals("Invalid account type. Please specify 'saving' or 'current'.", agent.receiveUserInput("random"));
    }

    @Test
    void receiveUserInput_SelectAccountTypeForModification_InvalidType() {
        agent.receiveUserInput("modify account name");
        assertEquals("Invalid account type. Please specify 'saving' or 'current'.", agent.receiveUserInput("random"));
    }


}

