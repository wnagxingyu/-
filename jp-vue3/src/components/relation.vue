<template>
  <div>
    <el-form :model="searchform" style="display: flex">
      <el-form-item label="成员1ID">
        <el-input v-model="searchform.membersId1" />
      </el-form-item>
      <el-form-item label="成员2ID">
        <el-input v-model="searchform.membersId2" />
      </el-form-item>
      <el-form-item style="margin-left: 10px">
        <el-button type="primary" @click="getData">搜索</el-button>
        <el-button type="danger" @click="add">新增</el-button>
      </el-form-item>
    </el-form>
    <el-table :data="tableData" stripe style="width: 100%">
      <el-table-column prop="membersId1" label="成员1ID" />
      <el-table-column prop="members1Name" label="成员1名字" />
      <el-table-column prop="membersId2" label="成员2ID" />
      <el-table-column prop="members2Name" label="成员2名字" />
      <el-table-column prop="relationType" label="关系" />
      <el-table-column label="操作">
        <template #default="scope">
          <el-button
            type="primary"
            size="small"
            @click.prevent="update(scope.row)"
          >
            编辑
          </el-button>
          <el-button
            type="danger"
            size="small"
            @click.prevent="deleteRow(scope.row)"
          >
            删除
          </el-button>
        </template>
      </el-table-column>
    </el-table>

    <el-dialog v-model="dialogFormVisible" title="新增">
      <el-form
        ref="ruleFormRef"
        :model="form"
        :rules="rules"
        label-width="120px"
        class="demo-ruleForm"
        :size="formSize"
        status-icon
      >
        <el-form-item
          label="成员1"
          :label-width="formLabelWidth"
          prop="membersId1"
        >
          <el-select v-model="form.membersId1" :disabled="form.id>0" placeholder="Select">
            <el-option
              v-for="item in options"
              :key="item.membersId"
              :label="item.membersName"
              :value="item.membersId"
            />
          </el-select>
        </el-form-item>
        <el-form-item
          label="成员2"
          :label-width="formLabelWidth"
          prop="membersId2"
        >
          <el-select v-model="form.membersId2" :disabled="form.id>0" placeholder="Select">
            <el-option
              v-for="item in options"
              :key="item.membersId"
              :label="item.membersName"
              :value="item.membersId"
            />
          </el-select>
        </el-form-item>
        <el-form-item
          label="关系"
          :label-width="formLabelWidth"
          prop="relationType"
        >
          <el-select v-model="form.relationType" placeholder="Select">
            <el-option
              v-for="item in options1"
              :key="item"
              :label="item"
              :value="item"
            />
          </el-select>
        </el-form-item>
        <el-form-item>
          <el-button type="primary" @click="submitForm(ruleFormRef)">
            提交
          </el-button>
          <el-button @click="resetForm(ruleFormRef)">关闭</el-button>
        </el-form-item>
      </el-form>
    </el-dialog>
  </div>
</template>

<script lang="ts" setup>
import { onMounted, ref, reactive } from "vue";
import axios from "axios";
import type { FormInstance, FormRules, ElMessage } from "element-plus";

const tableData = ref([]);

const options = ref([]);

const options1 = ref(["父子","父女","夫妻"]);

const dialogFormVisible = ref(false);

const flag = ref(true);

const formLabelWidth = "140px";

const searchform = reactive({
  membersId1: "",
  membersId2: ""
});

const formSize = ref("default");
const ruleFormRef = ref<FormInstance>();
let form = reactive({
  id: 0,
  membersId1: "",
  membersId2: "",
  relationType: "",
  updatetime: new Date(),
  deleted: 0
});
const rules = reactive<FormRules>({
  membersId1: [{ required: true, message: "请输入", trigger: "blur" }],
  membersId2: [{ required: true, message: "请输入", trigger: "blur" }],
  relationType: [{ required: true, message: "请输入", trigger: "blur" }],
});

const getData = () => {
  if(searchform.membersId1||searchform.membersId2){
    axios
    .post("http://localhost:8100/Relation/list2", searchform)
    .then((res: any) => {
      tableData.value = res.data;
    });
  }else{
    axios
    .get("http://localhost:8100/Relation/list")
    .then((res: any) => {
      tableData.value = res.data;
    });
  }
  
};

const getOptions = () => {
  axios.post("http://localhost:8100/Members/list2", {}).then((res: any) => {
    options.value = res.data;
  });
};

const add = () => {
  for (const key in form) {
    if (Object.prototype.hasOwnProperty.call(form, key)) {
      form[key] = null;
    }
  }
  flag.value = true
  dialogFormVisible.value = true;
};

const update = (data: object) => {
  form = Object.assign(form, data);
  form.id = 1
  flag.value = false
  dialogFormVisible.value = true;
};

const submitForm = async (formEl: FormInstance | undefined) => {
  if (!formEl) return;
  await formEl.validate((valid, fields) => {
    if (valid) {
      if (form.id<1) {
        form.updatetime = new Date()
      }
      axios
        .post(`http://localhost:8100/Relation/${form.id<1?'save':'mod'}`, form)
        .then((res: any) => {
          resetForm(formEl);
          ElMessage.success("操作成功");
          getData();
        })
        .catch(() => {
          ElMessage.success("操作失败");
        });
    } else {
      console.log("error submit!", fields);
    }
  });
};

const resetForm = (formEl: FormInstance | undefined) => {
  if (!formEl) return;
  formEl.resetFields();
  dialogFormVisible.value = false;
};

const deleteRow = (data: object) => {
  axios.post("http://localhost:8100/Relation/delete", data).then((res: any) => {
    ElMessage.success("删除成功");
    getData();
  });
};

const getTime = (time) => {
  if (time == null) {
    return "";
  }
  return time.substring(0, 10) + " " + time.substring(11, 19);
};

onMounted(() => {
  getData();
  getOptions();
});
</script>
