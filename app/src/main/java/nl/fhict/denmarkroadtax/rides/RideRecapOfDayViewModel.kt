package nl.fhict.denmarkroadtax.rides

data class RideRecapOfDayViewModel(
    val date: String,
    val costs: String,
    val average: String,
    val drivenKilometers: String,
    val drivenRides: String,
    val rides: List<RideViewModel>
)