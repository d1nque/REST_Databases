function getAllBooks(){
    $(function () {
        let container = $('#pagination');
        container.pagination({
            dataSource: function(done){
                $.ajax({
                    type: 'GET',
                    url: '/api/v1/book',
                    success: function(response){
                        done(response);
                    }
                });
            },
            pageSize: 15,
            callback: function (data, pagination) {
                var dataHtml = '<table class="table w-75 mt-5 me-auto ms-auto" id="bookTable">';
                dataHtml += '<thead><tr><th scope="col">Title</th><th scope="col">Author</th><th scope="col">Category</th></tr></thead><tbody>';

                $.each(data, function (index, item) {
                    dataHtml += '<tr><td><a onclick="getBook(' + item.id + ')">' + item.title + '</a></td>';
                    dataHtml += '<td>' + item.author + '</td>';
                    dataHtml += '<td>' + item.category.categoryName + '</td></tr>';
                });

                dataHtml += '</tbody></table>';

                $("#data-container").html(dataHtml);
            }
        })
    })
}

function getAllCategories(){
    $(function () {
        let container = $('#pagination');
        container.pagination({
            dataSource: function(done){
                $.ajax({
                    type: 'GET',
                    url: '/api/v1/category',
                    success: function(response){
                        done(response);
                    }
                });
            },
            pageSize: 15,
            callback: function (data, pagination) {
                var dataHtml = '<table class="table w-75 mt-5 me-auto ms-auto" id="categoryTable">';
                dataHtml += '<thead><tr><th scope="col">Category Name</th></tr></thead><tbody>';

                $.each(data, function (index, item) {
                    dataHtml += '<tr><td>' + item.categoryName + '</td></tr>';
                });

                dataHtml += '</tbody></table>';

                $("#data-container").html(dataHtml);
            }
        })
    })
}

function searchBooks(){
    $(function () {
        let container = $('#pagination');
        container.pagination({
            dataSource: function(done){
                $.ajax({
                    type: 'POST',
                    url: '/api/v1/book/search-book',
                    data: {
                        "author": document.getElementById("authorName").value,
                        "category": document.getElementById("categoryName").value
                    },
                    success: function(response){
                        done(response);
                    }
                });
            },
            pageSize: 15,
            callback: function (data, pagination) {
                var dataHtml = '<table class="table w-75 mt-5 me-auto ms-auto" id="bookTable">';
                dataHtml += '<thead><tr><th scope="col">Title</th><th scope="col">Author</th><th scope="col">Category</th></tr></thead><tbody>';

                $.each(data, function (index, item) {
                    dataHtml += '<tr><td><a onclick="getBook(' + item.id + ')">' + item.title + '</a></td>';
                    dataHtml += '<td>' + item.author + '</td>';
                    dataHtml += '<td>' + item.category.categoryName + '</td></tr>';
                });

                dataHtml += '</tbody></table>';

                $("#data-container").html(dataHtml);
            }
        })
    })
}

function clearDiv(){
    var el = document.getElementById("data-container");
    var el2 = document.getElementById("pagination");
    el.innerHTML = '';
    el2.innerHTML = '';
}

function getBook(id){
    clearDiv();
    var dataHtml = '<div class="row justify-content-center"><form class="w-50"><input type="text" class="form-control mt-3" id="newName" placeholder="New book name"><input type="text" class="form-control mt-3" id="newAuthor" placeholder="New Author name"><button type="button" class="btn btn-secondary mt-3" onclick="saveBook(' + id + ')">Save changes</button><button type="button" class="btn btn-secondary mt-3" onclick="deleteBook(' + id + ')">Delete</button></form><div>'

    $.ajax({
        type: 'GET',
        url: '/api/v1/book/' + id,
        success: function(data){
            // document.getElementById("saveButton").
            document.getElementById("newName").value = data.title;
            document.getElementById("newAuthor").value = data.author;
        }
    });

    $("#data-container").html(dataHtml);
}

function deleteBook(id){
    $.ajax({
        type: 'POST',
        url: '/api/v1/book/delete/' + id,
        success: function(){
            clearDiv();
        }
    });
}

function saveBook(id){
    $.ajax({
        type: 'GET',
        url: '/api/v1/book/' + id,
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json'
        },
        contentType: "application/json",
        success: function(data){
            data.title = document.getElementById("newName").value;
            data.author = document.getElementById("newAuthor").value;
            $.ajax({
                type: 'POST',
                url: '/api/v1/book/update-book',
                headers: {
                    'Accept': 'application/json',
                    'Content-Type': 'application/json'
                },
                contentType: "application/json",
                data: JSON.stringify(data),
                success: function(){
                    getAllBooks();
                }
            });
        }
    });
}

function addBook(){
    $.ajax({
        type: 'POST',
        url: '/api/v1/book/create-book',
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json'
        },
        data: JSON.stringify({
            "id": null,
            "title": document.getElementById("title").value,
            "author": document.getElementById("author").value,
            "category":{
                "id": document.getElementById("category").value
            }
        }),
        success: function(){
            getAllBooks();
        }
    });
}

function addBookForm(){
    clearDiv();
    var dataHtml = '<div class="row justify-content-center">\n' +
        '                <form class="w-50">\n' +
        '                    <label for="newName">Book title</label><input type="text" class="form-control mt-3" id="title" placeholder="Book title">\n' +
        '                    <label for="newAuthor">Author</label><input type="text" class="form-control mt-3" id="author" placeholder="Author name">\n' +
        '                    <label>Category\n' +
        '                        <select class="mb-5" id="category">\n' +
        '                            <option value="1" name="Fiction">Fiction</option>\n' +
        '                            <option value="2" name="Non-Fiction">Non-Fiction</option>\n' +
        '                            <option value="3" name="Science">Science</option>\n' +
        '                        </select>\n' +
        '                    </label>\n' +
        '                    <button type="button" class="btn btn-secondary mt-3 ms-5" onclick="addBook()">Add</button>\n' +
        '                </form>\n' +
        '            </div>'

    $("#data-container").html(dataHtml);
}