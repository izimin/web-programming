<template>
    <div class="loading-indicator"
         v-if="isDisplayed">
        <div class="spinner">
        </div>
    </div>
</template>

<script>
    export default {
        name: 'LoadingLocker',
        data () {
            return {}
        },
        computed:{
            isDisplayed() {
                return this.$store.getters.LOADING_INDICATION__IS_DISPLAYED;
            }
        }
    }

</script>

<style scoped>

    .loading-indicator {
        position: fixed;
        top: 0;
        left: 0;
        right: 0;
        bottom: 0;
        z-index: 10001;
        background: rgba(255, 255, 255, 0.1);
        display: flex;
        flex-direction: column;
        align-items: center;
        animation: fadeIn 0.3s 1 linear;

        > .spinner {
            margin: auto;
            width: 80px;
            height: 80px;
            border-radius: 50%;

            border-top: 8px solid rgba(0, 0, 0, 0.1);
            border-right: 8px solid rgba(0, 0, 0, 0.1);
            border-bottom: 8px solid rgba(0, 0, 0, 0.1);
            border-left: 8px solid rgba(0, 0, 0, 0.2);
            transform: translateZ(0);
            animation: spin 1.1s infinite linear;
        }

    }

    @keyframes spin {
        0% {
            transform: rotate(0deg);
        }
        100% {
            transform: rotate(360deg);
        }
    }

    @keyframes fadeIn {
        0% {
            opacity: 0;
        }
        100% {
            opacity: 1;
        }
    }

</style>
