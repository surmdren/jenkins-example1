def call(Closure commands) {
    timestamps {
        commands()
    }
}