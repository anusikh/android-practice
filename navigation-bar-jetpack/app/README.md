- import dependencies and setup retrofit and hilt (standard steps)
- create 2 new screens
- create model/BottomNavItem
- in util/Constants, add a object containing 2 BottomNavItems
- then create NavHostContainer inside the MainActivity
- then create MainScreen composable, this will contain a scaffold, that contains the NavigationBar
- then create `private lateinit var navController: NavHostController` and use it in onCreate with
  MainScreen()