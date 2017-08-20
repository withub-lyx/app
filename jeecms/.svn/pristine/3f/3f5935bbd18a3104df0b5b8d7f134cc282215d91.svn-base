var DataSourceTree = function(options) {
	this._data 	= options.data;
	this._delay = options.delay;
}

DataSourceTree.prototype.data = function(options, callback) {
	var self = this;
	var $data = null;

	if(!("name" in options) && !("type" in options)){
		$data = this._data;//the root tree
		callback({ data: $data });
		return;
	}
	else if("type" in options && options.type == "folder") {
		if("additionalParameters" in options && "children" in options.additionalParameters)
			$data = options.additionalParameters.children;
		else $data = {}//no data
	}
	
	if($data != null)//this setTimeout is only for mimicking some random delay
		setTimeout(function(){callback({ data: $data });} , parseInt(Math.random() * 500) + 200);

	//we have used static data here
	//but you can retrieve your data dynamically from a server using ajax call
	//checkout examples/treeview.html and examples/treeview.js for more info
};

var tree_data = {
	'free' : {name: '不交纳案件受理费的案件', type: 'folder'}	,
	'fortune' : {name: '财产案件', type: 'folder'}	,
	'fortuneless' : {name: '非财产案件', type: 'folder'}	,
	'administration' : {name: '行政案件', type: 'folder'}	,
	'intellectual_property' : {name: '知识产权案件', type: 'folder'}	,
	'application' : {name: '申请费', type: 'folder'}	,
	'other' : {name: '其他类型案件收费标准', type: 'folder'}	,
}
tree_data['free']['additionalParameters'] = {
	'children' : {
		'appliances' : {name: '依照民事诉讼法规定的特别程序审理的案件', type: 'item'},
		'arts-crafts' : {name: '裁定不予受理、驳回起诉、驳回上诉案件', type: 'item'},
		'clothing' : {name: '行政赔偿案件', type: 'item'},
		'computers' : {name: '对不予受理、驳回起诉和管辖权异议裁定不服，提起上诉的案件', type: 'item'},
	}
}
tree_data['fortune']['additionalParameters'] = {
	'children' : {
		'cars' : {name: '不超过1万元', type: 'item'},
		'motorcycles' : {name: '超过1万-10万', type: 'item'},
		'boats' : {name: '超过10万-20万', type: 'item'},
		'cars1' : {name: '超过20万-50万', type: 'item'},
		'motorcycles1' : {name: '超过50万-100万', type: 'item'},
		'boats1' : {name: '超过100万-200万', type: 'item'},
		'cars2' : {name: '超过200万-500万', type: 'item'},
		'motorcycles2' : {name: '超过500万-1000万', type: 'item'},
		'boats2' : {name: '超过1000万-2000万', type: 'item'},
		'cars3' : {name: '超过2000万', type: 'item'},
		
	}
}
/*tree_data['vehicles']['additionalParameters']['children']['cars']['additionalParameters'] = {
	'children' : {
		'classics' : {name: 'Classics', type: 'item'},
		'convertibles' : {name: 'Convertibles', type: 'item'},
		'coupes' : {name: 'Coupes', type: 'item'},
		'hatchbacks' : {name: 'Hatchbacks', type: 'item'},
		'hybrids' : {name: 'Hybrids', type: 'item'},
		'suvs' : {name: 'SUVs', type: 'item'},
		'sedans' : {name: 'Sedans', type: 'item'},
		'trucks' : {name: 'Trucks', type: 'item'}
	}
}*/

tree_data['fortuneless']['additionalParameters'] = {
	'children' : {
		'divorce' : {name: '离婚', type: 'folder'},
		'infract' : {name: '侵害姓名权、名称权、肖像权、名誉权、荣誉权以及其他人格权的案件', type: 'folder'},
		'others' : {name: '其他', type: 'item'}
	}
}
tree_data['fortuneless']['additionalParameters']['children']['divorce']['additionalParameters'] = {
		'children' : {
			'classics' : {name: '一般情况', type: 'item'},
			'convertibles' : {name: '涉及财产分割，财产总额不超过20万元人民币的', type: 'item'},
			'coupes' : {name: '超过20万的', type: 'item'},
			
		}
	}
tree_data['fortuneless']['additionalParameters']['children']['infract']['additionalParameters'] = {
		'children' : {
			'classics' : {name: '一般情况', type: 'item'},
			'convertibles' : {name: '涉及损害赔偿，赔偿金额不超过5万元人民币的', type: 'item'},
			'coupes' : {name: '超过5万元人民币至10万元人民币的', type: 'item'},
			'hatchbacks' : {name: '超过10万元人民币的', type: 'item'},
		}
	}
tree_data['administration']['additionalParameters'] = {
	'children' : {
		'apartments' : {name: '商标、专利、海事行政案件', type: 'item'},
		'villas' : {name: '其他行政案件', type: 'item'},
		
	}
}
tree_data['intellectual_property']['additionalParameters'] = {
	'children' : {
		'cats' : {name: '没有争议金额或者价额的', type: 'item'},
		'dogs' : {name: '有争议金额或者价额的', type: 'item'},
	}
}
tree_data['application']['additionalParameters'] = {
		'children' : {
			'cats' : {name: '执行申请费无需预交', type: 'folder'},
			'dogs' : {name: '申请保全措施', type: 'folder'},
			'cats1' : {name: '申请支付令', type: 'item'},
			'dogs1' : {name: '申请公示催告', type: 'item'},
			'cats2' : {name: '申请撤销仲裁裁决或者认定仲裁协议效力', type: 'item'},
			'dogs2' : {name: '申请破产', type: 'item'},
		}
	}
tree_data['application']['additionalParameters']['children']['cats']['additionalParameters'] = {
		'children' : {
			'classics' : {name: '没有执行金额或者价额的', type: 'item'},
			'convertibles' : {name: '不超过1万', type: 'item'},
			'coupes' : {name: '超过1万-50万', type: 'item'},
			'hatchbacks' : {name: '超过50万-500万', type: 'item'},
			'convertibles1' : {name: '超过500万-1000万', type: 'item'},
			'coupes2' : {name: '超过1000万', type: 'item'},
			'hatchbacks3' : {name: '未参加登记的权利人向人民法院提起诉讼的', type: 'item'},
		}
	}
tree_data['application']['additionalParameters']['children']['dogs']['additionalParameters'] = {
		'children' : {
			'classics' : {name: '财产数额不超过1000元人民币或者不涉及财产数额的', type: 'item'},
			'convertibles' : {name: '超过1000-10万', type: 'item'},
			'coupes' : {name: '超过10万', type: 'item'},
		}
	}
tree_data['other']['additionalParameters'] = {
		'children' : {
			'appliances' : {name: '以调解方式结案或者当事人申请撤诉的', type: 'item'},
			'arts-crafts' : {name: '适用简易程序审理的', type: 'item'},
			'clothing' : {name: '劳动争议案件', type: 'item'},
			'computers' : {name: '提出管辖异议，异议不成立的', type: 'item'},
			'arts-crafts1' : {name: '对财产案件提起上诉的', type: 'item'},
			'clothing1' : {name: '被告提起反诉、有独立请求权的第三人提出与本案有关的诉讼请求，需合并审理的', type: 'item'},
			'computers1' : {name: '需要交纳案件受理费的再审案件', type: 'item'},
		}
	}
var treeDataSource = new DataSourceTree({data: tree_data});

      
/*function search(){
	 var caseName = $("#case_name").val();
	 var tree_data_search = [];
	 $.each(tree_data,function(i,item){
		    if(item.name.includes(caseName)){
		    	tree_data_search[i] = item ;
		    }
		    $.each(tree_data[i].additionalParameters.children,function(j,item1){
		    		if(item1.name.includes(caseName)){
		    			tree_data_search[i] = item ;
		    			tree_data_search[i].additionalParameters.children = item1;
		    		}
		    		if(item1.type="folder"){
		    			$.each(item1.additionalParameters.children,function(k,item2){
		    				if(item2.name.includes(caseName)){
				    			tree_data_search[i] = item ;
				    			tree_data_search[i].additionalParameters.children = item1;
				    			tree_data_search[i].additionalParameters.children.additionalParameters = item2;
				    		}
		    				
		    			})
		    		}
		    })
		    
		    
	 })
	 var treeDataSource = new DataSourceTree({data: tree_data_search});
}
*/









var tree_data_2 = {
	'pictures'  : {name: 'Pictures',      type: 'folder',    'icon-class':'red'     }	,
	'music'     : {name: 'Music',         type: 'folder',    'icon-class':'orange'  }	,
	'video'     : {name: 'Video',         type: 'folder',    'icon-class':'blue'    }	,
	'documents' : {name: 'Documents',     type: 'folder',    'icon-class':'green'   }	,
	'backup'    : {name: 'Backup',        type: 'folder'}	,
	'readme'    : {name: '<i class="icon-file-text grey"></i> ReadMe.txt', type: 'item'},
	'manual'    : {name: '<i class="icon-book blue"></i> Manual.html', type: 'item'}
}
tree_data_2['music']['additionalParameters'] = {
	'children' : [
		{name: '<i class="icon-file-text   brown"></i> song1.ogg', type: 'item'},
		{name: '<i class="icon-file-text   brown"></i> song2.ogg', type: 'item'},
		{name: '<i class="icon-file-text   brown"></i> song3.ogg', type: 'item'},
		{name: '<i class="icon-file-text   brown"></i> song4.ogg', type: 'item'},
		{name: '<i class="icon-file-text   brown"></i> song5.ogg', type: 'item'}
	]
}
tree_data_2['video']['additionalParameters'] = {
	'children' : [
		{name: '<i class="icon-file-text  brown"></i> movie1.avi', type: 'item'},
		{name: '<i class="icon-file-text  brown"></i> movie2.avi', type: 'item'},
		{name: '<i class="icon-file-text  brown"></i> movie3.avi', type: 'item'},
		{name: '<i class="icon-file-text  brown"></i> movie4.avi', type: 'item'},
		{name: '<i class="icon-file-text  brown"></i> movie5.avi', type: 'item'}
	]
}
tree_data_2['pictures']['additionalParameters'] = {
	'children' : {
		'wallpapers' : {name: 'Wallpapers', type: 'folder', 'icon-class':'pink'},
		'camera' : {name: 'Camera', type: 'folder', 'icon-class':'pink'}
	}
}
tree_data_2['pictures']['additionalParameters']['children']['wallpapers']['additionalParameters'] = {
	'children' : [
		{name: '<i class="icon-file-text brown"></i> wallpaper1.jpg', type: 'item'},
		{name: '<i class="icon-file-text brown"></i> wallpaper2.jpg', type: 'item'},
		{name: '<i class="icon-file-text brown"></i> wallpaper3.jpg', type: 'item'},
		{name: '<i class="icon-file-text brown"></i> wallpaper4.jpg', type: 'item'}
	]
}
tree_data_2['pictures']['additionalParameters']['children']['camera']['additionalParameters'] = {
	'children' : [
		{name: '<i class="icon-file-text brown"></i> photo1.jpg', type: 'item'},
		{name: '<i class="icon-file-text brown"></i> photo2.jpg', type: 'item'},
		{name: '<i class="icon-file-text brown"></i> photo3.jpg', type: 'item'},
		{name: '<i class="icon-file-text brown"></i> photo4.jpg', type: 'item'},
		{name: '<i class="icon-file-text brown"></i> photo5.jpg', type: 'item'},
		{name: '<i class="icon-file-text brown"></i> photo6.jpg', type: 'item'}
	]
}


tree_data_2['documents']['additionalParameters'] = {
	'children' : [
		{name: '<i class="icon-file-text brown"></i> document1.pdf', type: 'item'},
		{name: '<i class="icon-file-text brown"></i> document2.doc', type: 'item'},
		{name: '<i class="icon-file-text brown"></i> document3.doc', type: 'item'},
		{name: '<i class="icon-file-text brown"></i> document4.pdf', type: 'item'},
		{name: '<i class="icon-file-text brown"></i> document5.doc', type: 'item'}
	]
}

tree_data_2['backup']['additionalParameters'] = {
	'children' : [
		{name: '<i class="icon-file-text brown"></i> backup1.zip', type: 'item'},
		{name: '<i class="icon-file-text brown"></i> backup2.zip', type: 'item'},
		{name: '<i class="icon-file-text brown"></i> backup3.zip', type: 'item'},
		{name: '<i class="icon-file-text brown"></i> backup4.zip', type: 'item'}
	]
}
var treeDataSource2 = new DataSourceTree({data: tree_data_2});