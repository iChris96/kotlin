/*

    MainActivity
        -Tiene un recyclerView
        -Observa la data (SingleLiveEvent<Post>)
        -Actualiza el recyclerView si la data cambia
    PostViewModel
        -Contiene la data (SingleLiveEvent<Post>)
        -Hace una solicitud al server
            getDataFromServer()
            -Ejecuta PostRespository.getDataCompleteData()
            -Recibe Either.R -> Retorna un Post(status=Sucess,posts = response,Failure = null)
            -Recibe Either.L -> Retorna un Post(status=Failure,posts = null,Failure = failure)
    GetPostIterator
        -Crea las corutinas
        -Caso de uso <List<Post>,Null> Return List<Post>, Recibe Null
        postRepository.getDataCompleteData() //Async
            -returns Either<Failure, list<post>>
    GetCommentIterator
        -Crea las corutinas
        -Caso de uso <List<Post>,id post> Return List<Post>, Recibe int
        postRepository.getCommentsFromPost(params) //Async
            -returns Either<Failure, list<comments>>
    PostRepository
        -Metodos para hacer llamados al servidor
        -getDataCompleteData() POST CALL
            isNetworkAvaible()
            -True-> Return Either
                request (Call -> service.getDataFromApi(), (T -> R) -> it, default -> emptyList())
                -Successful Call -> Return Either.Right //Response
                -Unsessful Call -> Return Either.Left //Failure -> Server Error
            -False-> Return Either.Left //Failure -> No internet
        -getCommentsFromPost(postId: Int) COMMENTS CALL
            isNetworkAvaible()
            -True-> Return Either
                request (Call -> service.getCommentsFromPost(postId), (T -> R) -> it, default -> emptyList())
                -Successful Call -> Return Either.Right //Response
                -Unsessful Call -> Return Either.Left //Failure -> Server Error
            -False-> Return Either.Left //Failure -> No internet
    PostService
        getDataFromApi()
            -return Call<List<Post>>
        getCommentsFromPost(postId: Int)
            -return Call<List<Comments>>




 */
