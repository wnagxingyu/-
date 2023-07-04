<template>
  <div>
    请选择家族：
    <el-select v-model="familyId" placeholder="Select" @change="getMembers">
      <el-option
        v-for="item in options"
        :key="item.familyId"
        :label="item.familyName"
        :value="item.familyId"
      />
    </el-select>
  </div>
  <div ref="treeContainer" class="family-tree"></div>
</template>

<script lang="ts">
import { defineComponent, ref, onMounted } from "vue";
import * as echarts from "echarts";
import axios from "axios";

export default defineComponent({
  name: "FamilyTree",
  data() {
    return {
      familyId: 0,
      options: [],
      list: [],
      rlist: [],
      flag: false,
      form: {},
      cdata: {},
      familyName: "",
    };
  },
  mounted() {
    this.getRelation();
  },
  methods: {
    getData() {
      axios.post("http://localhost:8100/Family/list2", {}).then((res: any) => {
        this.options = res.data || [];
        if (this.options.length > 0) {
          this.familyId = this.options[0].familyId;
          this.getMembers();
        }
      });
    },
    getRelation() {
      axios
        .get("http://localhost:8100/Relation/list")
        .then((res: any) => {
          this.rlist = res.data || [];
          this.getData();
        });
    },
    getMembers() {
      this.list = [];
      axios
        .post("http://localhost:8100/Members/chat", { familyId: this.familyId })
        .then((res: any) => {
          this.list = res.data;
          for (let i = 0; i < this.list.length; i++) {
            for (let j = 0; j < this.list.length; j++) {
              if (!this.list[j].pid) {
                this.list[j].pid = 0;
              }
              this.flag = false;
              for (let z = 0; z < this.rlist.length; z++) {
                if (
                  this.rlist[z].membersId1 == this.list[i].membersId &&
                  this.rlist[z].membersId2 == this.list[j].membersId
                ) {
                  this.form = this.rlist[z];
                  this.flag = true;
                  break;
                }
              }
              if (this.flag) {
                if (this.form.relationType == "夫妻") {
                  this.list[j].pid = this.form.membersId1 + "/0";
                } else {
                  this.list[j].pid = this.list[i].membersId;
                }
              }
            }
          }
          for (let i = 0; i < this.list.length; i++) {
            if ((this.list[i].pid+'').indexOf("/") > -1) {
              for (let j = 0; j < this.list.length; j++) {
                if (this.list[i].pid == (this.list[j].membersId + '/0')) {
                  this.list[i].pid = this.list[j].pid
                  this.list[i].membersName += "("+this.list[j].membersName+"妻子)" 
                }
              }
            }
          }
          this.cdata = this.getTreeList(this.list, "0", []);
          this.familyName = this.cdata[0].familyName;
          this.drawTree();
        });
    },
    getTreeList(list: Array<Object>, id: string, tree: any) {
      list.forEach((item) => {
        if (item.pid == id) {
          item.name = item.membersName;
          item.pid = id;
          tree.push(item);
        }
      });
      tree.forEach((item) => {
        item.children = [];
        this.getTreeList(list, item.membersId, item.children);
      });
      return tree;
    },
    drawTree() {
      const container = this.$refs.treeContainer;

      // 初始化echarts实例
      const chart = echarts.init(container);

      // 族谱数据
      const data = {
        name: this.familyName || "张氏",
        children: this.cdata,
      };

      // 转换数据格式
      const convertData = (node: any) => {
        const result = {
          name: node.name,
          children: [],
        };

        if (node.children) {
          node.children.forEach((child) => {
            result.children.push(convertData(child));
          });
        }

        return result;
      };

      const treeData = convertData(data);

      // 配置族谱图
      const option = {
        tooltip: {
          trigger: "item",
          triggerOn: "mousemove",
        },
        series: [
          {
            type: "tree",
            data: [treeData],
            top: "10%",
            bottom: "10%",
            layout: "vertical",
            label: {
              position: "left",
              verticalAlign: "middle",
              align: "right",
            },
            orient: "TB",
            leaves: {
              label: {
                position: "right",
                verticalAlign: "middle",
                align: "left",
              },
            },
            expandAndCollapse: true,
            initialTreeDepth: -1,
          },
        ],
      };

      // 渲染族谱图
      chart.setOption(option);
    },
  },
});
</script>

<style>
.family-tree {
  width: 100%;
  height: 600px;
}
</style>
