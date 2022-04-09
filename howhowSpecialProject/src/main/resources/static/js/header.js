header = `
<nav class="navbar navbar-expand-lg navbar-light bg-light p-0 m-0" style="height: 130px; background-color:#E2C652;"
		id="howhowdo-navbar">
		<div class="container-fluid px-3 header">
			<a class="navbar-brand" href="/">
				<img class="icon" src="/image/unnamed.png" alt="" />
				<!-- <i class="fa fa-book" aria-hidden="true"></i> -->
				<!-- <small><span>HowHowDo</span></small> -->
			</a>
			<div class="nav-item dropdown me-2 py-2 ms-5">
				<div class="dropdown-toggle" href="#" id="category" role="button" data-bs-toggle="dropdown"
					aria-expanded="false">
					<small><span>| 類別 |</span></small>
				</div>
				<ul class="dropdown-menu" aria-labelledby="category">
					<li class="dropdown-submenu dropdown-item" v-for="cate in categories">
						<!-- <a class="d-flex justify-content-between" tabindex="-1" :href=cate.cUrl>
							<small><span>{{ cate.cItemName }}</span></small>
							<i class="fa fa-chevron-right"></i></a> -->
						<div class="d-flex justify-content-between" @click="goToCoursePage('', cate.cateId)">
							<small><span>{{ cate.cItemName }}</span></small>
							<i class="fa fa-chevron-right"></i>
						</div>
					</li>
				</ul>
			</div>

			<div class="ms-3 navbar-search d-flex align-items-center justify-content-center">

				<input type="search" placeholder="搜尋任何事物..." aria-describedby="button-addon4" @keyup.enter="goToCoursePage('unkown')"
					class="navbar-search-input rounded-pill border-0 px-3">
				<div class="">
					<button id="button-addon4" type="button" class="border-0 rounded-pill" @click="goToCoursePage('unkown')"
						style="height: 40px; background-color:#E2C652;"><i class="fa fa-search"></i></button>
				</div>
			</div>

			<div class="collapse navbar-collapse d-flex justify-content-end" id="navbarNav">
				<div class="navbar-nav">
					<div class="nav-item dropdown mx-2 py-2 d-flex align-items-center">
						<div id="teacher" role="button" aria-expanded="false">
							<a class="nav-link" href="/teacherPage/page">
								<small><span>講師</span></small>
							</a>
						</div>
					</div>

					<div class="nav-item dropdown mx-2 py-2 d-flex align-items-center">
						<div id="my-course" role="button" aria-expanded="false">
							<a class="nav-link" href="/mycourse">
								<small><span>我的課程</span></small>
							</a>
						</div>
					</div>

					<div class="nav-item mx-2 py-2">
						<div id="shopping-cart" role="button" aria-expanded="false">
							<a class="nav-link" href="/myshop">
								<i class="fa fa-shopping-cart fa-2x" aria-hidden="true"></i>
							</a>
						</div>
					</div>


					<div class="nav-item mx-2 py-2 " v-if="!isLogged">
						<div href="" id="navbar-loggin" role="button" aria-expanded="false">
							<a style="color:#E2C652" class="nav-link" href="/login">
								<input type="button" class="bt2" value="登入"></input>
							</a>
						</div>
					</div>

					<div class="nav-item mx-2 py-2 " v-if="!isLogged">
						<div href="" id="navbar-register" role="button" aria-expanded="false">
							<a href="/register" class="nav-link">
								<input type="button" class="bt1" value="註冊"></input>
							</a>
						</div>
					</div>


					<div class="nav-item mx-2 py-2 " v-if="isLogged">
						<div href="" id="profile" role="button" aria-expanded="false">
							<a class="nav-link" href="/home">
								<i class="fa fa-user-circle fa-2x" aria-hidden="true"></i>
							</a>
						</div>
					</div>
				</div>
			</div>
		</div>
	</nav>
	`;
	document.write(header);