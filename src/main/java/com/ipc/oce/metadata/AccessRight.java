package com.ipc.oce.metadata;

/**
 * @author Rudikov Valentin
 */
public enum AccessRight {
    // reading - чтение
    Read,

    // adding - добавление
    Insert,

    // changing - изменение
    Update,

    // deletion - удаление
    Delete,

    // documents posting - проведение документов
    Posting,

    // documents posting cancel - отмена проведения документов
    UndoPosting,

    // viewing - просмотр
    View,

    // interactive insertion - интерактивное добавление
    InteractiveInsert,

    // editing
    Edit,

    // interactive deletion mark
    InteractiveSetDeletionMark,

    // interactive deletion mark cancel
    InteractiveClearDeletionMark,

    // interactive deletion of marked objects
    InteractiveDeleteMarked,

    // interactive posting
    InteractivePosting,

    // interactive posting (by standard form commands) of the document is in regular mode
    InteractivePostingRegular,

    // interactive posting cancel
    InteractiveUndoPosting,

    // interactive editing of the lead document. If the right is not established, the user cannot to remove,
    // establish the lead document a mark of removal, to repost or to make not lead. The form of such document opens in a mode of viewing
    InteractiveChangeOfPosted,

    // use of the input by string mode
    InputByString,

    // Accounting Register totals and Accumulation Register totals control (specification of the period that the totals are calculated for, and re-calculation of the totals)
    TotalsControl,

    // using
    Use,

    // direct interactive deletion
    InteractiveDelete,

    // infobase administration; "Data administration" right is required
    Administration,

    // data administration rights
    DataAdministration,

    // use of exclusive mode;
    ExclusiveMode,

    // view the list of active users
    ActiveUsers,

    // event log
    EventLog,

    // external connection
    ExternalConnection,

    // using automation
    Automation,

    // interactive opening external processors
    InteractiveOpenExtDataProcessors,

    // interactive opening external reports
    InteractiveOpenExtReports,

    // getting a value which is not stored in the database;
    Get,

    // setting a value which is not saved in the database;
    Set,

    // interactive activation
    InteractiveActivate,

    // start a business-process
    Start,

    // start a business-process interactively
    InteractiveStart,

    // task execution
    Execute,

    // interactive task execution
    InteractiveExecute,

    // printing, recording and copying to the clip board
    Output,

    // update database configuration
    UpdateDataBaseConfiguration,

    // thin client launch rights;
    ThinClient,

    // web-client launch rights;
    WebClient,

    // thick client launch rights;
    ThickClient,

    // viewing the configuration in the "All functions" mode rights;
    AllFunctionsMode,

    // saving user data (settings, favorites, history) rights
    SaveUserData,

    // user has rights to change his/her stored parameters for the standard authentication of external data source
    StandardAuthenticationChange,

    // user has rights to change parameters of standard authentication of the external data source for the current session.
    SessionStandardAuthenticationChange,

    // user has rights to change standard authentication parameters for the external data source for current session and user.
    SessionOSAuthenticationChange
}
