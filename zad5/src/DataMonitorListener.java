/**
 * Other classes use the DataMonitor by implementing this method
 */
public interface DataMonitorListener {
    /**
     * The existence status of the node has changed.
     */
    void exists(boolean exists);

    /**
     * The ZooKeeper session is no longer valid.
     *
     * @param rc
     *                the ZooKeeper reason code
     */
    void closing(int rc);
}