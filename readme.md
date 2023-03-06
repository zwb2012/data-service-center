1. ApplicationStartingEvent

2. ApplicationEnvironmentPreparedEvent：当Environment已经准备好，在context 创建前

3. ApplicationContextInitializedEvent：在ApplicationContext 创建和ApplicationContextInitializer都被调用后，但是bean definition没有被加载前

4. ApplicationPreparedEvent：bean definition已经加载，但是没有refresh

5. ApplicationStartedEvent： context 已经被refresh， 但是application 和command-line 的runner都没有被调用

6. AvailabilityChangeEvent

7. ApplicationReadyEvent： application 和command-line 的runner都被调用后触发

8. AvailabilityChangeEvent

9. ApplicationFailedEvent： 启动失败触发