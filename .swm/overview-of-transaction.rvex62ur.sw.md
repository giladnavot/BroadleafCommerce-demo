---
title: Overview of Transaction
---
A Transaction in the BroadleafCommerce-demo repository refers to the process of executing a set of operations as a single unit of work. It is represented by the `TransactionInfo` class, which contains information about an in-progress transaction, including thread and query information. The `TransactionInfo` class provides methods to get and set transaction definitions, manage entity managers, and handle other transaction-related operations. It also includes methods for initializing and clearing transactions. The `TransactionLifecycle` enum describes the key transaction lifecycle events being monitored by the system.

<SwmSnippet path="/common/src/main/java/org/broadleafcommerce/common/persistence/transaction/TransactionInfo.java" line="41">

---

# TransactionInfo Class

The TransactionInfo class is a POJO that holds information about an in-progress transaction. It includes thread and query information, and provides methods to get and set transaction definitions, manage entity managers, and handle other transaction-related tasks.

```java
public class TransactionInfo {

    public TransactionInfo() {
        initialize();
    }

    public TransactionInfo(EntityManager em, TransactionDefinition definition, boolean isCompressed, boolean isAbbreviated,
                           int abbreviatedLength, boolean decompressStatementForLog, int maxQueryListLength) {
        this.entityManager = new WeakReference<EntityManager>(em);
        this.definition = new WeakReference<TransactionDefinition>(definition);
        this.isCompressed = isCompressed;
        this.isAbbreviated = isAbbreviated;
        this.abbreviatedLength = abbreviatedLength;
        this.decompressStatementForLog = decompressStatementForLog;
        this.maxQueryListLength = maxQueryListLength;
        queries = new LinkedBlockingQueue<String>(maxQueryListLength==-1?Integer.MAX_VALUE:maxQueryListLength);
        compressedQueries = new LinkedBlockingQueue<CompressedItem>(maxQueryListLength==-1?Integer.MAX_VALUE:maxQueryListLength);
        initialize();
    }

    protected WeakReference<EntityManager> entityManager;
```

---

</SwmSnippet>

<SwmSnippet path="/common/src/main/java/org/broadleafcommerce/common/persistence/transaction/TransactionInfo.java" line="92">

---

# Transaction Definition

The getDefinition method is used to retrieve the transaction definition. The transaction definition includes properties such as propagation behavior, isolation level, timeout, and read-only status.

```java
    public TransactionDefinition getDefinition() {
        return definition.get();
    }
```

---

</SwmSnippet>

<SwmSnippet path="/common/src/main/java/org/broadleafcommerce/common/persistence/transaction/TransactionLifecycleMonitor.java" line="236">

---

# Transaction Execution

The TransactionLifecycleMonitor class uses the TransactionInfo class to log any in-progress TransactionInfo instances at the time of container shutdown. This helps in tracking and managing transactions effectively.

```java
            if (!infos.isEmpty()) {
                logger.support("Logging any in-progress TransactionInfo instances at the time of container shutdown");
                Long currentTime = System.currentTimeMillis();
                for (Map.Entry<Integer, TransactionInfo> entry : infos.entrySet()) {
                    TransactionInfo info = entry.getValue();
                    logger.support(String.format("TRANSACTIONMONITOR(5) - This transaction was detected as in-progress at the time " +
                        "of shutdown. The TransactionInfo has been alive for %s milliseconds. Logging TransactionInfo: \n%s",
                        currentTime - info.getStartTime(), info.toString()));
```

---

</SwmSnippet>

# Transaction Management Functions

Let's delve into the functions related to transaction management in the BroadleafCommerce-demo repository.

<SwmSnippet path="/common/src/main/java/org/broadleafcommerce/common/persistence/transaction/TransactionInfo.java" line="41">

---

## TransactionInfo Class

The TransactionInfo class encapsulates information about a transaction. It includes details such as the EntityManager associated with the transaction, the transaction definition, the start time of the transaction, and a list of queries executed within the transaction. The class also provides methods for logging statements and clearing transaction data.

```java
public class TransactionInfo {

    public TransactionInfo() {
        initialize();
    }

    public TransactionInfo(EntityManager em, TransactionDefinition definition, boolean isCompressed, boolean isAbbreviated,
                           int abbreviatedLength, boolean decompressStatementForLog, int maxQueryListLength) {
        this.entityManager = new WeakReference<EntityManager>(em);
        this.definition = new WeakReference<TransactionDefinition>(definition);
        this.isCompressed = isCompressed;
        this.isAbbreviated = isAbbreviated;
        this.abbreviatedLength = abbreviatedLength;
        this.decompressStatementForLog = decompressStatementForLog;
        this.maxQueryListLength = maxQueryListLength;
        queries = new LinkedBlockingQueue<String>(maxQueryListLength==-1?Integer.MAX_VALUE:maxQueryListLength);
        compressedQueries = new LinkedBlockingQueue<CompressedItem>(maxQueryListLength==-1?Integer.MAX_VALUE:maxQueryListLength);
        initialize();
    }

    protected WeakReference<EntityManager> entityManager;
```

---

</SwmSnippet>

<SwmSnippet path="/common/src/main/java/org/broadleafcommerce/common/persistence/transaction/TransactionLifecycleMonitor.java" line="187">

---

## TransactionLifecycleMonitor Class

The TransactionLifecycleMonitor class monitors the lifecycle of transactions and logs relevant information. It maintains a map of TransactionInfo instances, keyed by the hash code of the associated EntityManager. The class provides methods for starting and stopping the monitor, detecting and logging long-running transactions, and finalizing transactions.

```java
    protected Map<Integer, TransactionInfo> infos = new ConcurrentHashMap<>();
    protected boolean isStarted = false;
    protected boolean enabled = false;
    protected Timer timer = new Timer("TransactionLifecycleMonitorThread", true);

    @PostConstruct
    public synchronized void init() {
        if (!isStarted) {
            if (instance == null) {
                instance = (TransactionLifecycleMonitor) context.getBean("blTransactionLifecycleMonitor");
            }
            if (isAtLeastOneTransactionManagerEnabled()) {
                timer.schedule(new TimerTask() {
                    @Override
                    public void run() {
                        groomInProgressTransactionInfos();
                    }
                }, loggingPollingResolution, loggingPollingResolution);
                enabled = true;
            }
            isStarted = true;
```

---

</SwmSnippet>

&nbsp;

*This is an auto-generated document by Swimm AI 🌊 and has not yet been verified by a human*

<SwmMeta version="3.0.0" repo-id="Z2l0aHViJTNBJTNBQnJvYWRsZWFmQ29tbWVyY2UtZGVtbyUzQSUzQWdpbGFkbmF2b3Q=" repo-name="BroadleafCommerce-demo" doc-type="overview"><sup>Powered by [Swimm](/)</sup></SwmMeta>
