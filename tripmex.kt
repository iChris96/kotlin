
 Fragments[
     Login[
        SignInFragment{
            -Inherits: RegisterNavigator
            -ViewModel: UserViewModel<RegisterNavigator>
                -onViewCreated() -> userViewModel.setNavigator(this) //init BaseViewModel.navigator
                -facebookRegister() -> userViewModel.registerOrLoginWithFacebook //facebook.click
                -observables() -> userViewModel.failure.observe() //Obeserve BaseViewModel.SingleLiveEvent<Failure?>
            -val firebaseToken:String // Main App Firebase Token

            /* Functions */
            -override fun onCreateView()
                -inflater.inflate(R.layout.fragment_menu_fragment)

            -override fun onViewCreated() /* init BaseViewModel.navigator, init(), observables() */
                -userViewModel.setNavigator(this) //BaseViewModel.navigator = this(SignInFragment)
                -init() //Firebase token, facebook callback manager, buttons listeners
                -observables() //Observe -> BaseViewModel.Failure

            -fun init() /* getToken(), create callbackManager Instance, initListeners() */
                *onViewCreated()
                -getToken() //Get main app Firebase token
                -callbackManager = CallbackManager.Factory.create() //Create facebook callbackManager instance
                -initListeners() //Init buttons click listeners

            -fun initListeners() /* emailRegisterButton, facebookRegisterButton, signInButton */
                *init()
                -emailRegisterButton.click
                    -action.facebookInfo = null //argument
                    -navigate(action) // -> navigate to navigateToRegisterFragment
                -signInButton.click // -> navigate to navigate_to_loginFragment
                -facebookRegisterButton.click //facebookRegister()

            -fun facebookRegister() /* facebook register process */
                *initListeners(facebookRegisterButton.click)
                -onSuccess
                    -userViewModel.registerOrLoginWithFacebook
                        (accessToken: loginResult.accessToken, firebaseToken: firebaseToken, uuid: getUUID(requireContext()))
                -onCancel //Log onCancel
                -onError //showErrorSnackbar

            -fun observables() /* Observes BaseViewModel.Failure */
                *onViewCreated()
                -userViewModel.failure.observe() //BaseViewModel.SingleLiveEvent<Failure?>
                    -if it(failure) == null -> return @observer //failure == null -> return @observer
                    -else showErrorSnackbar(it.message) //failure != null -> showErrorSnackbar(failure.message)

            -override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) /* Callback for facebook register */
                -callbackManager?.onActivityResult(requestCode, resultCode, data) //Hear some response
                -super.onActivityResult(requestCode, resultCode, data) //?

            /* RegisterNavigator Interface */
            -override fun goToRegisterWithFacebook(facebookGraphResponse: FacebookGraphResponse)
                -action.facebookInfo = facebookGraphResponse //argument
                -navigate(action) // -> navigate to navigateToRegisterFragment

            -override fun goToMainScreen()
                -startActivityNewTask<MainMenuActivity> 
                -activity.finish

            -override fun goToVerifyPhone()
                -action.isUserLogged = false //argument
                -navigate(action) //  -> navigate to navigateToPhoneFragment

            -override fun goToAddReferrals()
                -navigate(action) //  -> navigate to navigateToAddReferralsFragment

            -override fun goToPendingReferrals()
                -navigate(action) //  -> navigate to navigateToPendingReferralsFragment

            -override fun goToVerifyEmail()
                -navigate(action) //  -> navigate to navigateToValidateEmail

            /* End RegisterNavigator Interface */

            -fun getToken() /* Get Firebase App Token */
                *init()
                -FirebaseInstanceId.getInstance().instanceId //firebaseToken = task.result?.token ?: "Token"
        },
     ],
     Register[
        RegisterFragment{
            -Inherits: RegisterNavigator
            -ViewModel: UserViewModel<RegisterNavigator>
                -onViewCreated() -> userViewModel.setNavigator(this) //init BaseViewModel.navigator
                -initObservers() -> userViewModel.failure.observe() //BaseViewModel.SingleLiveEvent<Failure?>
                -initObservers() -> userViewModel.loading.observe() //BaseViewModel.SingleLiveEvent<Boolean>
                -register() -> userViewModel.registerUser(Name,LastName,Email,Password,token,getUUID(requireContext()))
            -val token:String // Main App Firebase Token

            /* Functions */
            -override fun onCreateView()
                -inflater.inflate(R.layout.fragment_register)

            -override fun onViewCreated() /* set BaseViewModel.navigator, init() */
                -userViewModel.setNavigator(this) //BaseViewModel.navigator = this(RegisterFragment)
                -init() //Firebase token
            
            -fun init() /* getToken(), setUpTermsAndPolicy(), initListener(), initObservers() */
                *onViewCreated()
                -getToken() //Get main app Firebase token
                -setUpTermsAndPolicyView()  //tvTermsAndPolicy links
                -initListeners() //init buttons click listeners
                -initObservers() //Observe -> BaseViewModel.Failure, BaseViewModel.Loading
            
            -fun getToken() /* Get Firebase App Token */
                *init()
                -FirebaseInstanceId.getInstance().instanceId //token = task.result?.token ?: "Token"
            
            -fun setUpTermsAndPolicyView() /* Links to pdfs on tvTermsAndPolicy */
                *init()
                -wordToSpan = SpannableString(getString("Estoy de acuerdo con los terminos...")) //SpannableSting
                -wordToSpan.setSpan //links to pdf files
                -tvTermsAndPolicy.text = wordToSpan //set wordToSpan to tvTermsAndPolicy.text from fragment

            -fun initListeners() /* backButton.click, RegisterButton.click, Done-key */
                *init()
                -ivBack.click //back arrow -> return to SignInFragment
                -btnRegister.click //register()
                -confirmPasswordEditText.click.doOnDone //password2 done-key click
                    -hideKeyboard() //Hide keyboard
                    -register() //register()


            -fun initObservers() /* Observe -> BaseViewModel.Failure, BaseViewModel.Loading */
                *init()
                -userViewModel.failure.observe() //BaseViewModel.SingleLiveEvent<Failure?>
                    -if it(failure) == null -> return @observer //failure == null -> return @observer
                    -else showErrorSnackbar(it.message) //failure != null -> showErrorSnackbar(failure.message)
                -userViewModel.loading.observe() //BaseViewModel.SingleLiveEvent<Boolean>
                    - if true // progressBar animation visible()
                    -else // progressBar animation gone()

            -fun register() /* Validate all fields, if every field is good then registerUser */
                *initListeners(btnRegister.click)
                -booleanArrayOf //good -> true, bad -> false
                        (/*EmptyName?,EmptyLastName?,validEmail?,PasswordIsGood?,PasswordsAreEquals?,Terms&PolicyChecked?*/)
                    .reduce(acc, b -> acc && b) //apply AND to all values to get unique value
                    .isSuccess //if result from reduce is true then action
                        -userViewModel.registerUser //action
                            (Name,LastName,Email,Password,token,getUUID(requireContext()))

            -fun isConfirmPasswordEqual() /* Check if passwords are equals */
                *register

            /* RegisterNavigator Interface */

            -override fun goToMainScreen()
                cleanFields()

             -override fun goToVerifyPhone()
                -cleanFields()
                -action.isUserLogged = false //argument
                -navigate(action) //  -> navigate to navigateToPhoneFragment

            /* End RegisterNavigator Interface */

            -fun cleanFields() /* Clean inputs */
                *goToMainScreen, goToVerifyPhone
        },
        PhoneFragment{
            -Inherits: RegisterNavigator
            -ViewModel: UserViewModel<RegisterNavigator>
                -onViewCreated() -> userViewModel.setNavigator(this) //init BaseViewModel.navigator
                -initObservers() -> userViewModel.failure.observe() //BaseViewModel.SingleLiveEvent<Failure?>
                -initObservers() -> userViewModel.loading.observe() //BaseViewModel.SingleLiveEvent<Boolean>
                -validatePhoneNumber() -> sendSmsVerification() //SMS Request
                -validatePhoneNumber() -> userViewModel.smsVerificationLiveData.observe() //UserViewModel.MutableLiveData<Boolean>
            -args: PhoneFragment by navArgs()
                -isUserLogged: Boolean

            /* Functions */
            -override fun onCreateView()
                -inflater.inflate(R.layout.fragment_phone)

            -override fun onViewCreated() /* init BaseViewModel.navigator, initListeners(), initObservables() */
                -userViewModel.setNavigator(this) //BaseViewModel.navigator = this(PhoneFragment)
                -if (!args.isUserLogged) { closeButton.visible() closeButton.setOnClickListener { userViewModel.logOut() }
                } else closeButton.gone() //hide closeButton
                initListeners() //btnAction.click, done-key.click
                initObservers() //Observe Failure & Loading

            -fun initListeners() /* btnAction.click & done-key.click */
                *onViewCreated()
                btnAction.setOnClickListener { validatePhoneNumber() } //NextButton.click
                etPhoneNumber.doOnDone { hideKeyboard() validatePhoneNumber() } //NumberEditText done-key click
            
            -fun validatePhoneNumber() /* Send SMS to phone number */
                *initListeners(btnAction.click)
                etPhoneNumber.validatePhone //Validate phone number 
                    -showErrorSnackbar(R.string.error_invalid_phone_number) //Wrong number
                    .isSuccess { //Good number, lets send a msg
                        -userViewModel.sendSmsVerification //SMS Request
                            (SmsVerificationRequest(etPhoneNumber.textValue(), LADA_NUMBER)) //phoneNumber, CountryLada
                        -userViewModel.smsVerificationLiveData.observe(this, //smsVerificationLiveData -> MutableLiveData<Boolean> 
                            Observer { goToCodeValidation(it) }) //Wait for sms then goToCodeValidation

            -fun goToCodeValidation(isCodeValid: Boolean) /* Sms send, go to navigateToValidationCode fragment */
                *validatePhoneNumber
                -if (!isCodeValid) return //something wrong -> return
                -userViewModel.smsVerificationLiveData.value = false //default value -> true success send msg - false default
                -try
                    -action.phoneNumber = etPhoneNumber.textValue() //argument
                    -action.isUserLogged = args.isUserLogged //argument
                    -findNavController().navigate(action) //navigate to navigateToValidationCode
                
            -fun initObservers() /* Observe -> BaseViewModel.Failure, BaseViewModel.Loading */
                *onViewCreated()
                -userViewModel.failure.observe() //BaseViewModel.SingleLiveEvent<Failure?>
                    -failure?.let { showErrorSnackbar(it.message) } //failure != null -> showErrorSnackbar(failure.message)
                -userViewModel.loading.observe() //BaseViewModel.SingleLiveEvent<Boolean>
                    -if (it) progressBar.visible() else progressBar.gone() //progressBar animation visible() or gone()
  
            /* RegisterNavigator Interface */
            
            -override fun goToLoginScreen()
                - navigate // -> navigate to signInFragment
                        
            /* End RegisterNavigator Interface */
        },
        ValidationCodeFragment{
            -Inherits: RegisterNavigator
            -ViewModel: UserViewModel<RegisterNavigator>
                -onCreateView() -> userViewModel.setNavigator(this) //init BaseViewModel.navigator
                -initObservers() -> userViewModel.failure.observe() //BaseViewModel.SingleLiveEvent<Failure?>
                -initObservers() -> userViewModel.loading.observe() //BaseViewModel.SingleLiveEvent<Boolean>
                -initObservers() -> userViewModel.smsVerificationLiveData.observe() //UserViewModel.MutableLiveData<Boolean>
                -checkSMSCode() -> checkSMSCode(CheckSMSRequest) //Code 
                -showDialog() -> sendSmsVerification() //SMS Request

            -args: ValidationCodeFragmentArgs? = null
                -isUserLogged: Boolean
                -phoneNumber: String
            -var validationCode:String? = null //full 4 digits code

            /* Functions */
            -override fun onCreateView()
                -arguments?.let { args = ValidationCodeFragmentArgs.fromBundle(arguments ?: Bundle.EMPTY) } //get arguments
                -userViewModel.setNavigator(this) //BaseViewModel.navigator = this(SignInFragment)
                -inflater.inflate(R.layout.fragment_validation_code)

            -override fun onViewCreated() /* initListeners(), initObservers(), get args.phoneNumber */
                -initListeners()
                -initObservers()
                -phoneNumber = args?.phoneNumber //get phoneNumber arg
                -tvContent.text = getString(R.string.code_verification_content, phoneNumber) //Te hemos enviado un sms al numero...
            
            -fun initListeners() /* Click events */
                *onViewCreated()
                -btnAction.click //button(next step)
                    -validateCode()
                -tvSendAgain.click //textView(have not received code yet?)
                    -showDialog()
                -etOne.doOnTextChanged
                    -if txt.lenght == 1 -> etTwo.requestFocus()
                -etTwo.doOnTextChanged
                    -if txt.lenght == 1 -> etThree.requestFocus()
                -etThree.doOnTextChanged
                    -if txt.lenght == 1 -> etFour.requestFocus()
                -etFour.doOnTextChanged
            
            -fun validateCode() /* Validate 4 digits Code then checkSMSCode() */
                *initListeners(btnAction.click)
                -validationCode = etOne.trimText() + etTwo.trimText() + etThree.trimText() + etFour.trimText() //Link numbers
                -validationCode?.let
                    -if (it.isEmpty() || it.length < 4 || !it.isNumber()) { //validate code
                        showErrorSnackbar(getString(R.string.error_verification_code_invalid)) //alert invalid code
                    -else checkSMSCode()

            -fun checkSMSCode() /* Send valid code and get userInfo as response */
                *validateCode()
                -if (validationCode != null && phoneNumber != null) //if code is correct then..
                    -userViewModel.checkSMSCode(CheckSMSRequest( // -> onPhoneVerified(response as userInfo)
                            phoneNumber ?: "", //phone number
                            LADA_NUMBER.toString(), //lada
                            (validationCode))) //full 4 digits code
            
            /* RegisterNavigator Interface */
            -override fun onPhoneVerified(response: UpdatePhoneResponse) /* get userInfo */
                *checkSMSCode()
                showDoneDialog() //runs correct code animation and execute as lambda the following block of code
                    if (args?.isUserLogged == false)
                        val hasReferrals = response.verifiedData?.referrals ?: 0 > 0 //get referrals
                        val isEmailVerified = response.verifiedData?.emailVerified == true //get isEmailVerified?
                        if (hasReferrals && isEmailVerified) //go to MainMenuActivity
                            requireContext().startActivityNewTask<MainMenuActivity>()
                        else if (!hasReferrals) // -> navigate to ReferralsFragment
                            findNavController().navigate(ValidationCodeFragmentDirections.navigateToReferrals())
                        else if (!isEmailVerified) // -> navigate to VerifyEmailFragment
                            findNavController().navigate(ValidationCodeFragmentDirections.actionToEmailValidation())
                        else // -> navigate to ReferralsFragment
                            findNavController().navigate(ValidationCodeFragmentDirections.navigateToReferrals())
                    else // -> navigate back to PhoneFragment
                        findNavController().popBackStack(R.id.phoneFragment, true)
            /* End RegisterNavigator Interface */
            
            -fun showDialog() /* Change phone number or resend SMS */
                *initListeners(tvSendAgain.click)
                -alert(R.string.phone_verification_dialog_title, R.string.phone_verification_dialog_content)
                    cancelable = true
                    -positiveButton(R.string.general_change) //button(change number)
                        -activity?.onBackPressed() //back to phone fragment
                    -negativeButton(R.string.general_resend) //button(resend code)
                        -phoneNumber?.let
                            -userViewModel.sendSmsVerification(SmsVerificationRequest(it, LADA_NUMBER)) //SMS new Request
                .show()

            -fun initObservables() /*Observe -> Failure, Loading, smsVerificarionLiveData */
                *onViewCreated()
                -userViewModel.failure.observe() //BaseViewModel.SingleLiveEvent<Failure?>
                -userViewModel.loading.observe() //BaseViewModel.SingleLiveEvent<Boolean>
                -userViewModel.smsVerificationLiveData.observe() //smsVerificationLiveData -> MutableLiveData<Boolean>
                    -Toast("Se ha enviado un nuevo codigo") //Toast msg


        },
     ]

 ]

ViewModels[
    UserViewModel{

        fun logOut() {
            isLoading.postValue(true) //Enable Load Animation
    
            //logOutInteractor.invoke(params:Params, onResult:(Either<Failure, Type>) -> Unit)
            //logOutInteractor.invoke(UseCase.None(), onResult{it: Either<Failure, Unit>})
            jobs.add(logOutInteractor(UseCase.None()) {
                it.either(::handleFailure) {
                    isLoading.postValue(false)
                    when (getNavigator()) {
                        is MenuFragmentNavigator -> (getNavigator() as MenuFragmentNavigator).goToLoginScreen()
                        is RegisterNavigator -> (getNavigator() as RegisterNavigator).goToLoginScreen()
                        is ReferralNavigator -> (getNavigator() as ReferralNavigator).goToLoginScreen()
                    }
                }
            })
        }

        fun logOut()
            isLoading.postValue(true)

            jobs.add(logOutInteractor())

    }
]


































*/